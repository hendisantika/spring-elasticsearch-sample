package com.hendisantika.springelasticsearchsample;

import com.hendisantika.springelasticsearchsample.config.DefaultProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringElasticsearchSampleApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringElasticsearchSampleApplication.class, args); SpringApplication app = new SpringApplication(SpringBootApp.class);
//        app.setDefaultProperties(DefaultProperties.getDefaultProperties());
//        app.run(args);
		SpringApplication app = new SpringApplication(SpringElasticsearchSampleApplication.class);
		app.setDefaultProperties(DefaultProperties.getDefaultProperties());
		app.run(args);
	}

}
