package com.example.settingservice.dto;

import com.example.settingservice.model.Setting;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class SettingResponse {
    private String id;
    private String theme;
    private String language;
    private String modelName;
    private String systemPrompt;
    private UUID userId;

    public static SettingResponse fromSetting(Setting setting) {
        SettingResponse settingResponse = new SettingResponse();
        settingResponse.setId(setting.getId());
        settingResponse.setTheme(setting.getTheme());
        settingResponse.setLanguage(setting.getLanguage());
        settingResponse.setModelName(setting.getModelName());
        settingResponse.setSystemPrompt(setting.getSystemPrompt());
        settingResponse.setUserId(setting.getUserId());
        return settingResponse;
    }
}
