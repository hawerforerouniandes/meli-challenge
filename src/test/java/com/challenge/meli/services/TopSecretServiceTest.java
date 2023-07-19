package com.challenge.meli.services;

import com.challenge.meli.dto.request.SatelliteRequestDto;
import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.dto.response.TopSecretResponseDto;
import com.challenge.meli.services.message.IMessageService;
import com.challenge.meli.services.position.IPositionService;
import com.challenge.meli.services.satellite.ISatelliteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopSecretServiceTest {

    @InjectMocks
    private TopSecretService service;
    @Mock
    private IPositionService positionService;
    @Mock
    private ISatelliteService satelliteService;
    @Mock
    private IMessageService messageService;

    @Test
    void addSatelliteDataSuccess(){
        String satelliteName =  "kenobi";
        String[] message = {"este", "", "", "mensaje", ""};
        float distance = Float.valueOf(100);

        Map<String, TopSecretSplitRequestDto> satelliteDataMap = new HashMap<>();
        TopSecretSplitRequestDto topSecretSplitRequestDto = new TopSecretSplitRequestDto();
        topSecretSplitRequestDto.setDistance(distance);
        topSecretSplitRequestDto.setMessage(message);
        satelliteDataMap.put(satelliteName, topSecretSplitRequestDto);

        service.addSatelliteData("kenobi", topSecretSplitRequestDto);

        when(satelliteService.getSatelliteData()).thenReturn(satelliteDataMap);

        Map<String, TopSecretSplitRequestDto> getSatelliteData = satelliteService.getSatelliteData();

        assertEquals(getSatelliteData.size() , 1);
    }

    @Test
    void addSatelliteDataException(){
        String satelliteName =  "kenobi";
        try {
            service.addSatelliteData("kenobi", null);
            fail("The exception has not been thrown");
        } catch (Exception e) {
            assertThat(e).isNotNull();
        }

    }

    @Test
    void getTopSecretDataException(){

        CompletableFuture<TopSecretResponseDto> response = null;

        float[] distances = {};
        float[][] positions = {};

        when(satelliteService.getDistances()).thenReturn(distances);
        when(satelliteService.getPositions()).thenReturn(positions);

        try {
            response = service.getTopSecretData();
            fail("The exception has not been thrown");
        } catch (Exception e) {
            assertThat(response).isNull();
        }

    }

    @Test
    void addTopSecretDataSuccess() throws ExecutionException, InterruptedException {
        TopSecretRequestDto request = new TopSecretRequestDto();
        request.setSatellites(new ArrayList<SatelliteRequestDto>());

        SatelliteRequestDto satelliteKenobi = new SatelliteRequestDto();
        satelliteKenobi.setName("kenobi");
        satelliteKenobi.setDistance(Float.valueOf(100));
        satelliteKenobi.setMessage(new String[]{"este", "", "", "mensaje", ""});
        request.getSatellites().add(satelliteKenobi);

        SatelliteRequestDto satelliteKywalker = new SatelliteRequestDto();
        satelliteKywalker.setName("skywalker");
        satelliteKywalker.setDistance(Float.valueOf(115.5F));
        satelliteKywalker.setMessage(new String[]{"", "es", "", "", "secreto"});
        request.getSatellites().add(satelliteKywalker);

        SatelliteRequestDto satelliteSato = new SatelliteRequestDto();
        satelliteSato.setName("sato");
        satelliteSato.setDistance(Float.valueOf(142.7F));
        satelliteSato.setMessage(new String[]{"este", "", "un", "", ""});
        request.getSatellites().add(satelliteSato);

        float[] distances = {satelliteKenobi.getDistance(), satelliteKywalker.getDistance(), satelliteSato.getDistance()};
        float[][] positions = {{-500,-200},{100,-100},{500,100}};
        float[] location = {1,1};

        when(satelliteService.getDistances()).thenReturn(distances);
        when(satelliteService.getPositions()).thenReturn(positions);
        when(positionService.getLocation(distances,positions )).thenReturn(location);

        CompletableFuture<TopSecretResponseDto> response = service.addTopSecretData(request);

        assertThat(response.get()).isNotNull();
    }

    @Test
    void addTopSecretDataException() {
        CompletableFuture<TopSecretResponseDto> response = null;
        try {
            response = service.addTopSecretData(null);
            fail("The exception has not been thrown");
        } catch (Exception e) {
            assertThat(response).isNull();
        }

    }
}
