package com.kkimleang.settingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableElasticsearchRepositories(basePackages = "com.kkimleang.settingservice.elastic")
@EnableJpaRepositories(basePackages = "com.kkimleang.settingservice.repository")
@SpringBootApplication
public class SettingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SettingServiceApplication.class, args);
    }

}
