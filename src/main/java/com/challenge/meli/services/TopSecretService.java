package com.challenge.meli.services;

import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.response.TopSecretResponseDto;
import com.challenge.meli.services.message.IMessageService;
import com.challenge.meli.services.position.IPositionService;
import com.challenge.meli.services.satellite.ISatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopSecretService implements ITopSecretService{

    private ISatelliteService satelliteService;
    private IPositionService positionService;
    private IMessageService messageService;

    @Autowired
    public TopSecretService(ISatelliteService satelliteService,
                            IPositionService positionService,
                            IMessageService messageService)
    {
        this.satelliteService = satelliteService;
        this.positionService = positionService;
        this.messageService = messageService;
    }

    @Override
    public TopSecretResponseDto topSecret(TopSecretRequestDto request) {
        try {
            TopSecretResponseDto response = new TopSecretResponseDto();

            float[] distances = satelliteService.getDistances(request);
            String[][] messages = satelliteService.getMessages(request);

            float[] positions = positionService.getLocation(distances);
            response.getPosition().setX(positions[0]);
            response.getPosition().setY(positions[1]);

            response.setMessage(messageService.getMessage(messages));

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
