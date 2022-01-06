package com.vognev.textilewebproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TextileWebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextileWebProjectApplication.class, args);
	}

}
