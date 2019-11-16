package com.inclination.scaffold;
import java.util.Properties;

import com.inclination.scaffold.utils.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.pagehelper.PageHelper;
//import com.pengzu.sms.annotion.EnabledPengzuSmsAutoConfiguration;

/***
 * 很久都没有开源了,"com.pengzu.sms"
 */
//@EnabledPengzuSmsAutoConfiguration
@EnableApolloConfig
@SpringBootApplication
@MapperScan(basePackages = "com.inclination.scaffold.infrastraction.repository",markerInterface = MyMapper.class)
public class ScaffoldApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ScaffoldApplication.class, args);
	}
	/**
	 * pageHelper 分页管理
	 * @return
	 */
	@Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
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
