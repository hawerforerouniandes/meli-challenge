package com.challenge.meli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAsync
public class MeliChallengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeliChallengeApplication.class, args);
	}

	@RestController
	class HomeController {
		@GetMapping("/")
		public String health() {
			return "Meli chanllege API!";
		}
	}
}
