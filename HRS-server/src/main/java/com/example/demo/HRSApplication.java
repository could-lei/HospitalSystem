package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//HRS：Hospital Registration System 医院挂号系统
@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class HRSApplication {

	public static void main(String[] args) {
		SpringApplication.run(HRSApplication.class, args);
	}
}
