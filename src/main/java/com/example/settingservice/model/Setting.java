package com.example.settingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_settings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"})
})
public class Setting {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    public Setting() {
        this.id = UUID.randomUUID().toString();
    }

    private String theme = "dark";
    private String language;
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "system_prompt")
    private String systemPrompt;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
