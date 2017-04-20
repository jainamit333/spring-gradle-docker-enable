package com.tw;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.tw")
@Configuration
public class TradeawayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeawayApplication.class, args);
	}




	@Bean
	public VelocityEngine velocityEngine(){

		return new VelocityEngine();

	}





}
