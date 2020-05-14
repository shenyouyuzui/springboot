package com.tvcp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

@MapperScan("com.tvcp.mapper")
@SpringBootApplication(scanBasePackages = "com")
@EnableTransactionManagement
public class RetrieveApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RetrieveApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(RetrieveApplication.class, args);
	}

}
