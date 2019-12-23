package com.inclination.scaffold.constant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "scaffold.tool.project")
public class ToolProjectProperties {

    private String gitUrl;

    private String gitAdmin;

    private String gitPassword;

    private String jenkinsUrl;

    private String jenkinsAdmin;

    private String jenkinsPassword;
}
