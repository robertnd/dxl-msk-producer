package com.safaricom.dxl.streaming.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "")
public @Data class ApplicationConfig {
    // TODO: flesh out class
    /*
    @PostConstruct
    public void setConsumerName() throws UnknownHostException {
        consumerName = InetAddress.getLocalHost().getHostName() + UUID.randomUUID();
    }*/
}

