package com.demo.tasklist.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties {
    private final String platform;
    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;
}
