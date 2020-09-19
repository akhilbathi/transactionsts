package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootBankTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBankTransactionApplication.class, args);
	}
	@Bean
    @LoadBalanced
    public RestTemplate getTemplate() {
   	return new RestTemplate();
   }


}
