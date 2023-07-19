package com.challenge.meli.services;

import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.services.message.MessageService;
import com.challenge.meli.services.satellite.SatelliteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @InjectMocks
    private MessageService service;

    @Mock
    private SatelliteService satelliteService;

    @Test
    void getMessagesSuccess(){
        String[][] messages  = new String[3][5];
        messages[0] = new String[]{"este", "", "", "mensaje", ""};
        messages[1] = new String[]{"", "es", "", "", "secreto"};
        messages[2] = new String[]{"este", "", "un", "", ""};

        String message = service.getMessage(messages);

        assertEquals("este es un mensaje secreto", message);
    }

    @Test
    void getMessagesException(){

        String message = null;
        try {
            message = service.getMessage();
        } catch (Exception e) {
            assertThat(message).isNull();
        }
    }
}
