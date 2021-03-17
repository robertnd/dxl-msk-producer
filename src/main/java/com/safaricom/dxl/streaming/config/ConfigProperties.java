package com.safaricom.dxl.streaming.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Data
public class ConfigProperties {
    private String streamName;
    private String userName;
    private String password;
    private String secretKey;
}
