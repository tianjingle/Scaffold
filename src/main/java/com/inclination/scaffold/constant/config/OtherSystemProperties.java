package com.inclination.scaffold.constant.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Data
@Configuration
@ConfigurationProperties(prefix = "scaffold.tool.project")
public class OtherSystemProperties {

    private String gitUrl;

    private String gitAdmin;

    private String gitPassword;

    private String jenkinsUrl;

    private String jenkinsAdmin;

    private String jenkinsPassword;

    @Value("${scaffold.tool.maven.script:mvn archetype:generate -DgroupId={0} -DartifactId={1} -Dversion={2} -Dpackage={3}  -DarchetypeGroupId=net.cnki -DarchetypeArtifactId=discuss-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -B -DarchetypeCatalog=local -DinteractiveMode=false}")
    private String script;

    @Value("${scaffold.tool.maven.script:dev}")
    private String env;

}
