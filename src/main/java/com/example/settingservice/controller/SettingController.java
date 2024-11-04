package com.example.settingservice.controller;

import com.example.settingservice.dto.Response;
import com.example.settingservice.dto.SettingRequest;
import com.example.settingservice.dto.SettingResponse;
import com.example.settingservice.model.Setting;
import com.example.settingservice.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingController {
    private final SettingRepository settingRepository;

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
            return Response.<SettingResponse>ok()
                    .setPayload(SettingResponse.fromSetting(savedSetting));
        } catch (Exception e) {
            return Response.<SettingResponse>exception()
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
