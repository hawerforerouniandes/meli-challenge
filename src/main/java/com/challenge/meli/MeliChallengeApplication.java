package com.challenge.meli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MeliChallengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeliChallengeApplication.class, args);
	}

}
