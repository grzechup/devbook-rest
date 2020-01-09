package com.pk.thesis.devbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pk.*")
public class DevbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevbookApplication.class, args);
	}

}
