package com.besmartexim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "User Activity Management API", version = "1.0", description = "User Activity Related APIs"))
@SpringBootApplication
public class BesmarteximActivityManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BesmarteximActivityManagementApplication.class, args);
	}

}
