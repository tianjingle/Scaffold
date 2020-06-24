package com.inclination.scaffold;

import com.inclination.scaffold.utils.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

/***
 * 很久都没有开源了,"com.pengzu.sms"
 */
@SpringBootApplication
@MapperScan(basePackages = "com.inclination.scaffold.infrastraction.repository",markerInterface = MyMapper.class)
public class ScaffoldApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ScaffoldApplication.class, args);
	}

    /**
     * 配置Spring Boot支持外置Tomcat容器中部署
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ScaffoldApplication.class);
    }
}
