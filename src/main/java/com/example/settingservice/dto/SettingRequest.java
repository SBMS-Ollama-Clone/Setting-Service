package com.example.settingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class SettingRequest {
    private String theme;
    private String language;
    private String modelName;
    private String systemPrompt;
    private UUID userId;
}
