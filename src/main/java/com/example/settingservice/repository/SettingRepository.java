package com.example.settingservice.repository;

import com.example.settingservice.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SettingRepository extends JpaRepository<Setting, String> {
    Setting findByUserId(UUID userId);
}
