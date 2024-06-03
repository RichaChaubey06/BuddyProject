package com.ust.buddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = {"com.ust.buddy"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class},scanBasePackages = {"com.ust.buddy"})
@ComponentScan(basePackages = {"com.ust.buddy"})
@EntityScan(value = "com.ust.buddy")
public class BuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuddyApplication.class, args);
	
	}

}
