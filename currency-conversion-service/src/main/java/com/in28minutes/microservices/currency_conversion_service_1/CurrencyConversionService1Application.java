package com.in28minutes.microservices.currency_conversion_service_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConversionService1Application {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionService1Application.class, args);
	}

}
