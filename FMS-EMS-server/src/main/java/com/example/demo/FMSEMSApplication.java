package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan(basePackages = {"com.example.demo.mapper"})
@SpringBootApplication
public class FMSEMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(FMSEMSApplication.class, args);
	}
}
