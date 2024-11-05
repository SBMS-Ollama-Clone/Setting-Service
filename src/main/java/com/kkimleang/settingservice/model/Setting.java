package com.kkimleang.settingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.UUID;

@Document(indexName = "settings")
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_settings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"})
})
public class Setting {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    public Setting() {
        this.id = UUID.randomUUID().toString();
    }

    @Field(name = "theme")
    private String theme = "dark";
    @Field(name = "language")
    private String language;
    @Field(name = "model_name")
    @Column(name = "model_name")
    private String modelName;
    @Field(name = "system_prompt")
    @Column(name = "system_prompt")
    private String systemPrompt;
    @Field(name = "user_id")
    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
