package com.pachico.arithmetic;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@OpenAPIDefinition
@EnableRetry
public class ArithmeticApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArithmeticApiApplication.class, args);
	}

}
