package com.challenge.meli;

import com.challenge.meli.services.TopSecretService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MeliChallengeApplicationTests {
	@InjectMocks
	private TopSecretService service;

	@Test
	void contextLoads() {
		assertThat(service).isNotNull();
	}
}
