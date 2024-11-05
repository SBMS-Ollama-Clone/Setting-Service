package com.kkimleang.settingservice.controller;

import com.kkimleang.settingservice.dto.Response;
import com.kkimleang.settingservice.dto.SettingRequest;
import com.kkimleang.settingservice.dto.SettingResponse;
import com.kkimleang.settingservice.elastic.SettingSearchRepository;
import com.kkimleang.settingservice.model.Setting;
import com.kkimleang.settingservice.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingController {
    private final SettingRepository settingRepository;
    private final SettingSearchRepository settingSearchRepository;

    @GetMapping("/of-users/{userId}")
    public Response<SettingResponse> getSettingByUserId(@PathVariable String userId) {
        try {
            Setting setting = settingRepository.findByUserId(UUID.fromString(userId));
            if (setting == null) {
                Setting newSetting = new Setting();
                newSetting.setTheme("dark");
                newSetting.setUserId(UUID.fromString(userId));
                Setting savedSetting = settingRepository.save(newSetting);
                return Response.<SettingResponse>ok()
                        .setPayload(SettingResponse.fromSetting(savedSetting));
            } else {
                return Response.<SettingResponse>ok()
                        .setPayload(SettingResponse.fromSetting(setting));
            }
        } catch (Exception e) {
            return Response.<SettingResponse>exception()
                    .setErrors(e.getMessage());
        }
    }

    @PostMapping("/create")
    public Response<SettingResponse> saveSettingByUserId(@RequestBody SettingRequest settingRequest) {
        try {
            Setting setting = new Setting();
            setting.setTheme(settingRequest.getTheme());
            setting.setLanguage(settingRequest.getLanguage());
            setting.setModelName(settingRequest.getModelName());
            setting.setSystemPrompt(settingRequest.getSystemPrompt());
            setting.setUserId(settingRequest.getUserId());
            Setting savedSetting = settingRepository.save(setting);
            savedSetting = settingSearchRepository.save(savedSetting);
            return Response.<SettingResponse>ok()
                    .setPayload(SettingResponse.fromSetting(savedSetting));
        } catch (Exception e) {
            return Response.<SettingResponse>exception()
                    .setErrors(e.getMessage());
        }
    }

    @GetMapping
    public Response<List<SettingResponse>> getAllSettings(@RequestParam int page, @RequestParam int size) {
        try {
            Pageable pageable = Pageable.ofSize(size).withPage(page);
            List<Setting> settings = settingSearchRepository.findAll(pageable).getContent();
            return Response.<List<SettingResponse>>ok()
                    .setPayload(SettingResponse.fromSettings(settings));
        } catch (Exception e) {
            return Response.<List<SettingResponse>>exception()
                    .setErrors(e.getMessage());
        }
    }

    @PutMapping("/{settingId}/update")
    public Response<SettingResponse> updateSettingByUserId(@PathVariable String settingId, @RequestBody SettingRequest settingRequest) {
        try {
            Setting setting = settingRepository.findById(settingId).orElse(null);
            if (setting == null) {
                return Response.<SettingResponse>notFound().setErrors("Setting with id " + settingId + " not found");
            } else {
                setting.setTheme(settingRequest.getTheme());
                setting.setLanguage(settingRequest.getLanguage());
                setting.setModelName(settingRequest.getModelName());
                setting.setSystemPrompt(settingRequest.getSystemPrompt());
                Setting updatedSetting = settingRepository.save(setting);
                return Response.<SettingResponse>ok()
                        .setPayload(SettingResponse.fromSetting(updatedSetting));
            }
        } catch (Exception e) {
            return Response.<SettingResponse>exception()
                    .setErrors(e.getMessage());
        }
    }
}
