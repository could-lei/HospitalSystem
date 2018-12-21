package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//物资库存管理系统 器材购入系统 器材保养维修系统
@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class MIMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(MIMSApplication.class, args);
	}
}
