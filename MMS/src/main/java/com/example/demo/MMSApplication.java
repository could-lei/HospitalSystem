package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//MMS: 	Medicine Management System 药物管理系统
@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class MMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(MMSApplication.class, args);
	}
}
