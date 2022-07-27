package net.maku.storage.config;

import net.maku.api.module.storage.StorageService;
import net.maku.storage.enums.StorageTypeEnum;
import net.maku.storage.properties.StorageProperties;
import net.maku.storage.service.AliyunStorageService;
import net.maku.storage.service.LocalStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 存储配置文件
 *
 * @author 阿沐 babamu@126.com
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
@ConditionalOnProperty(prefix = "storage", value = "enabled")
public class StorageConfiguration {

    @Bean
    public StorageService storageService(StorageProperties properties){
        if(properties.getConfig().getType() == StorageTypeEnum.LOCAL){
            return new LocalStorageService(properties);
        }else if(properties.getConfig().getType() == StorageTypeEnum.ALIYUN){
            return new AliyunStorageService(properties);
        }

        return null;
    }

}
