package com.challenge.meli.services;

import com.challenge.meli.dto.request.SatelliteRequestDto;
import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.dto.response.TopSecretResponseDto;
import com.challenge.meli.repositories.ISatelliteRepository;
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
    public TopSecretResponseDto addTopSecretData(TopSecretRequestDto request) {
        try {
            for (SatelliteRequestDto satelliteRequestDto: request.getSatellites()) {
                TopSecretSplitRequestDto topSecretSplitRequestDto = new TopSecretSplitRequestDto();
                topSecretSplitRequestDto.setDistance(satelliteRequestDto.getDistance());
                topSecretSplitRequestDto.setMessage(satelliteRequestDto.getMessage());
                addSatelliteData(satelliteRequestDto.getName(), topSecretSplitRequestDto);
            }
            return getTopSecretData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addSatelliteData(String satelliteName, TopSecretSplitRequestDto request) {
        satelliteService.setSatelliteData(satelliteName, request);
    }

    @Override
    public TopSecretResponseDto getTopSecretData() {
        try {
            TopSecretResponseDto response = new TopSecretResponseDto();

            float[] distances = satelliteService.getDistances();
            float[][] positions = satelliteService.getPositions();
            String[][] messages  = satelliteService.getMessages();

            float[] location = positionService.getLocation(distances, positions);
            response.getPosition().setX(location[0]);
            response.getPosition().setY(location[1]);

            response.setMessage(messageService.getMessage(messages));

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
