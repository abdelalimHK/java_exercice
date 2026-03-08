package com.renault.ggva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.renault.ggva")
public class ExoGgvarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExoGgvarApplication.class, args);
	}

}
