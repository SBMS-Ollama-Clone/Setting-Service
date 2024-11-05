package com.kkimleang.settingservice.elastic;

import com.kkimleang.settingservice.model.Setting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingSearchRepository extends ElasticsearchRepository<Setting, String> {

}
