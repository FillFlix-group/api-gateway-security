package com.lms.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableEurekaClient
@ComponentScan({ "com.lms.apigateway", "com.lms.apigateway.rdbmserver" })
public class ApiGatewaySecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewaySecurityApplication.class, args);
	}

}
