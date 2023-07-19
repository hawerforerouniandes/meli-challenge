package com.challenge.meli.services;

import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.models.Position;
import com.challenge.meli.models.Satellite;
import com.challenge.meli.repositories.ISatelliteRepository;
import com.challenge.meli.services.satellite.SatelliteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SatelliteServiceTest {

    @InjectMocks
    private SatelliteService service;
    @Mock
    private ISatelliteRepository repository;

    @Test
    void setSatelliteDataSuccess(){
        TopSecretSplitRequestDto topSecretSplitRequestDto = new TopSecretSplitRequestDto();
        topSecretSplitRequestDto.setDistance(100.0F);
        topSecretSplitRequestDto.setMessage(new String[]{"este", "", "", "mensaje", ""});

        service.setSatelliteData("kenobi", topSecretSplitRequestDto);

        assertThat(service).isNotNull();
    }

    @Test
    void getSatelliteDataSuccess(){
        Map<String, TopSecretSplitRequestDto> getSatelliteData = service.getSatelliteData();
        assertThat(getSatelliteData).isNotNull();
    }

    @Test
    void getDistancesSuccessException(){
        float[] distances = null;
        try {
            ReflectionTestUtils.setField(service, "satellitesSize", "3");
            distances = service.getDistances();
            fail("The exception has not been thrown");
        } catch (Exception e) {
            assertThat(distances).isNull();
        }
    }

    @Test
    void getPositionsException(){
        float[][] positions = null;
        try {
            positions = service.getPositions();
            fail("The exception has not been thrown");
        } catch (Exception e) {
            assertThat(positions).isNull();
        }
    }

    @Test
    void getMessagesSuccess(){
        TopSecretSplitRequestDto topSecretSplitKenobi = new TopSecretSplitRequestDto();
        topSecretSplitKenobi.setDistance(100.0F);
        topSecretSplitKenobi.setMessage(new String[]{"este", "", "", "mensaje", ""});
        service.setSatelliteData("kenobi", topSecretSplitKenobi);

        TopSecretSplitRequestDto topSecretSplitSkywalker = new TopSecretSplitRequestDto();
        topSecretSplitSkywalker.setDistance(115.5F);
        topSecretSplitSkywalker.setMessage(new String[]{"", "es", "", "", "secreto"});
        service.setSatelliteData("skywalker", topSecretSplitSkywalker);

        TopSecretSplitRequestDto topSecretSplitSato = new TopSecretSplitRequestDto();
        topSecretSplitSato.setDistance(142.7F);
        topSecretSplitSato.setMessage(new String[]{"este", "", "un", "", ""});
        service.setSatelliteData("sato", topSecretSplitSato);

        String[][] messages = service.getMessages();

        assertThat(messages).isNotNull();
    }

    @Test
     void getDistancesSuccess(){
        TopSecretSplitRequestDto topSecretSplitKenobi = new TopSecretSplitRequestDto();
        topSecretSplitKenobi.setDistance(100.0F);
        topSecretSplitKenobi.setMessage(new String[]{"este", "", "", "mensaje", ""});
        service.setSatelliteData("kenobi", topSecretSplitKenobi);

        TopSecretSplitRequestDto topSecretSplitSkywalker = new TopSecretSplitRequestDto();
        topSecretSplitSkywalker.setDistance(115.5F);
        topSecretSplitSkywalker.setMessage(new String[]{"", "es", "", "", "secreto"});
        service.setSatelliteData("skywalker", topSecretSplitSkywalker);

        TopSecretSplitRequestDto topSecretSplitSato = new TopSecretSplitRequestDto();
        topSecretSplitSato.setDistance(142.7F);
        topSecretSplitSato.setMessage(new String[]{"este", "", "un", "", ""});
        service.setSatelliteData("sato", topSecretSplitSato);

        ReflectionTestUtils.setField(service, "satellitesSize", "3");

        float[] distances = service.getDistances();

        assertEquals(distances.length, 3);

    }

    @Test
    void getPositionSuccess(){
        String nameKenobi = "kenobi";
        String nameSkywalker = "skywalker";
        String nameSato = "sato";

        TopSecretSplitRequestDto topSecretSplitKenobi = new TopSecretSplitRequestDto();
        topSecretSplitKenobi.setDistance(100.0F);
        topSecretSplitKenobi.setMessage(new String[]{"este", "", "", "mensaje", ""});
        service.setSatelliteData(nameKenobi, topSecretSplitKenobi);

        TopSecretSplitRequestDto topSecretSplitSkywalker = new TopSecretSplitRequestDto();
        topSecretSplitSkywalker.setDistance(115.5F);
        topSecretSplitSkywalker.setMessage(new String[]{"", "es", "", "", "secreto"});
        service.setSatelliteData(nameSkywalker, topSecretSplitSkywalker);

        TopSecretSplitRequestDto topSecretSplitSato = new TopSecretSplitRequestDto();
        topSecretSplitSato.setDistance(142.7F);
        topSecretSplitSato.setMessage(new String[]{"este", "", "un", "", ""});
        service.setSatelliteData(nameSato, topSecretSplitSato);

        ReflectionTestUtils.setField(service, "satellitesSize", "3");

        Position positionKenobi = new Position(-500, -200);
        Satellite satelliteKenobi = new Satellite(nameKenobi, positionKenobi);

        Position positionSkywalker = new Position(100, -100);
        Satellite satelliteSkywalker = new Satellite(nameSkywalker, positionSkywalker);

        Position positionSato = new Position(500, 100);
        Satellite satelliteSato = new Satellite(nameSato, positionSato);


        when(repository.getSatellite(nameKenobi)).thenReturn(satelliteKenobi);
        when(repository.getSatellite(nameSkywalker)).thenReturn(satelliteSkywalker);
        when(repository.getSatellite(nameSato)).thenReturn(satelliteSato);

        float[][] positions = service.getPositions();

        assertEquals(positions.length, 3);

    }

    @Test
    void getMessagesException(){
        String[][] messages = null;
        try {
            service.setSatelliteData(null, null);
            messages = service.getMessages();
            fail("The exception has not been thrown");
        } catch (Exception e) {
            assertThat(messages).isNull();
        }
    }

}
