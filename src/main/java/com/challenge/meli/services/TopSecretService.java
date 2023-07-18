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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

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
    @Async
    public CompletableFuture<TopSecretResponseDto> addTopSecretData(TopSecretRequestDto request) {
        try {
            if(request == null)
                throw new Exception("request is null");

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
    @Async
    public void addSatelliteData(String satelliteName, TopSecretSplitRequestDto request) {
        try {
            if(request == null)
                throw new Exception("request is null");
            satelliteService.setSatelliteData(satelliteName, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public CompletableFuture<TopSecretResponseDto> getTopSecretData() {
        try {
            TopSecretResponseDto response = new TopSecretResponseDto();

            float[] distances = satelliteService.getDistances();
            float[][] positions = satelliteService.getPositions();
            String[][] messages  = satelliteService.getMessages();

            if(distances.length == 0 || distances.length == 0 || distances.length == 0)
                throw new Exception("There is not enough information");

            float[] location = positionService.getLocation(distances, positions);
            response.getPosition().setX(location[0]);
            response.getPosition().setY(location[1]);

            response.setMessage(messageService.getMessage(messages));

            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
