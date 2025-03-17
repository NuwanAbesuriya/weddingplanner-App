package com.IT0033.weddingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BudgetTrackingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetTrackingServiceApplication.class, args);
	}

}
