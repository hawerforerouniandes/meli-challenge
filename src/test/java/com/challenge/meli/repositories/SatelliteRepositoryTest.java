package com.challenge.meli.repositories;

import com.challenge.meli.models.Satellite;
import com.challenge.meli.services.message.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SatelliteRepositoryTest {

    @InjectMocks
    private SatelliteRepository repository;

    @Mock
    private Environment environment;

    @Test
    void getAllSatellitesSuccess(){
        when(environment.getProperty("satellites.size")).thenReturn("3");

        when(environment.getProperty("satellites[0].name")).thenReturn("kenobi");
        when(environment.getProperty("satellites[0].positionX", Integer.class)).thenReturn(-500);
        when(environment.getProperty("satellites[0].positionY", Integer.class)).thenReturn(-200);

        when(environment.getProperty("satellites[1].name")).thenReturn("skywalker");
        when(environment.getProperty("satellites[1].positionX", Integer.class)).thenReturn(100);
        when(environment.getProperty("satellites[1].positionY", Integer.class)).thenReturn(-100);

        when(environment.getProperty("satellites[2].name")).thenReturn("sato");
        when(environment.getProperty("satellites[2].positionX", Integer.class)).thenReturn(500);
        when(environment.getProperty("satellites[2].positionY",Integer.class)).thenReturn(100);

        List<Satellite> satellites =  repository.getAllSatellites();

        assertEquals(satellites.size() , 3);

    }

    @Test
    void getAllSatellitesException(){

        List<Satellite> satellites =  null;
        try {
            satellites =  repository.getAllSatellites();

        } catch (Exception e) {
            assertThat(satellites).isNull();
        }
    }

    @Test
    void getSatelliteSuccess(){
        when(environment.getProperty("satellites.size")).thenReturn("3");

        when(environment.getProperty("satellites[0].name")).thenReturn("kenobi");
        when(environment.getProperty("satellites[0].positionX", Integer.class)).thenReturn(-500);
        when(environment.getProperty("satellites[0].positionY", Integer.class)).thenReturn(-200);

        Satellite satellite =  repository.getSatellite("kenobi");

        assertEquals("kenobi" , satellite.getName());

    }

    @Test
    void getAllSatelliteException(){

        Satellite satellite =  null;
        try {
            satellite =  repository.getSatellite("kenobi");

        } catch (Exception e) {
            assertThat(satellite).isNull();
        }
    }
}
