package com.challenge.meli.services;

import com.challenge.meli.services.position.PositionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTest {

    @InjectMocks
    private PositionService positionService;

    @Test
    void getLocationSuccess(){
        float[] distances = {100.0F, 115.5F, 142.7F};
        float[][] positions = {{-500,-200},{100,-100},{500,100}};
        float[] location = positionService.getLocation(distances, positions);
        assertEquals(-80.10876F, location[0]);
        assertEquals(-100.796196F, location[1]);
    }

    @Test
    void getLocationException(){
        float[] location = null;
        try {
            float[] distances = {100.0F, 115.5F};
            float[][] positions = {{-500,-200},{100,-100},{500,100}};
            location = positionService.getLocation(distances, positions);
        } catch (Exception e) {
            assertThat(location).isNull();
        }
    }
}
