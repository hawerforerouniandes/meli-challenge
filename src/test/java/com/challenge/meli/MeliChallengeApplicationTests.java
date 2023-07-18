package com.challenge.meli;

import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.services.TopSecretService;
import com.challenge.meli.services.message.IMessageService;
import com.challenge.meli.services.position.IPositionService;
import com.challenge.meli.services.satellite.ISatelliteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MeliChallengeApplicationTests {

	@ExtendWith(MockitoExtension.class)
	@InjectMocks
	private TopSecretService service;

	@Mock
	private IPositionService positionService;
	@Mock
	private IMessageService messageService;
	@Mock
	private ISatelliteService satelliteService;

	@Test
	void addSatelliteData(){
		String satelliteName =  "kenobi";
		String[] message = {"este", "", "", "mensaje", ""};
		float distence = Float.valueOf(100);

		Map<String, TopSecretSplitRequestDto> satelliteDataMap = new HashMap<>();
		TopSecretSplitRequestDto topSecretSplitRequestDto = new TopSecretSplitRequestDto();
		topSecretSplitRequestDto.setDistance(distence);
		topSecretSplitRequestDto.setMessage(message);
		satelliteDataMap.put(satelliteName, topSecretSplitRequestDto);

		service.addSatelliteData("kenobi", topSecretSplitRequestDto);

		when(satelliteService.getSatelliteData()).thenReturn(satelliteDataMap);

		Map<String, TopSecretSplitRequestDto> getSatelliteData = satelliteService.getSatelliteData();

		assertEquals(getSatelliteData.size() , 1);
	}

}
