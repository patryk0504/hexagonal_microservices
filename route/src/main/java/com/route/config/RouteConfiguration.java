package com.route.config;

import com.google.maps.GeoApiContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RouteConfigurationProperties.class)
public class RouteConfiguration {

    @Bean
    public GeoApiContext geoApiContext(RouteConfigurationProperties routeConfigurationProperties) {
        return new GeoApiContext.Builder()
                .apiKey(routeConfigurationProperties.getApiKey())
                .build();
    }
}
