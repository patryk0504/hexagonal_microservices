package com.route.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "webclient")
public class WebClientConfigurationProperties {
    private String url;
    private int timeout;
}
