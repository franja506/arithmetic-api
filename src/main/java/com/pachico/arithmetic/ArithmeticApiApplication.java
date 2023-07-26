package com.pachico.arithmetic;

import com.pachico.arithmetic.adapter.utils.RateLimitInterceptor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@OpenAPIDefinition
@EnableRetry
public class ArithmeticApiApplication implements WebMvcConfigurer {

	@Autowired
	@Lazy
	private RateLimitInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
				.addPathPatterns("/**");
	}

	public static void main(String[] args) {
		SpringApplication.run(ArithmeticApiApplication.class, args);
	}

}
