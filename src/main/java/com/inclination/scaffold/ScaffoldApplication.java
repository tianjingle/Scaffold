package com.inclination.scaffold;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.pagehelper.PageHelper;
//import com.pengzu.sms.annotion.EnabledPengzuSmsAutoConfiguration;

@EnableApolloConfig
@SpringBootApplication
//@EnabledPengzuSmsAutoConfiguration
@ComponentScan(basePackages={"com.inclination.scaffold","com.pengzu.sms"})
@MapperScan("com.inclination.scaffold.infrastraction.repository")
public class ScaffoldApplication {

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
}
