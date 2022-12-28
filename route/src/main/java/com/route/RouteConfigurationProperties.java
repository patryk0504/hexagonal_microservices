package com.route;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "route")
public class RouteConfigurationProperties {
    private String apiKey;
}
