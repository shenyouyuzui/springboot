package com.tkrs;

import org.apache.catalina.core.ApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@MapperScan("com.tkrs.mapper")
@SpringBootApplication(scanBasePackages = "com.tkrs")
@EnableTransactionManagement
public class CbondModelApplication extends SpringBootServletInitializer implements WebApplicationInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CbondModelApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(CbondModelApplication.class, args);
	}

}
