package com.challenge.meli.services.satellite;

import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.models.Satellite;
import com.challenge.meli.repositories.ISatelliteRepository;
import com.challenge.meli.services.message.IMessageService;
import com.challenge.meli.services.position.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SatelliteService implements ISatelliteService{

    private final Map<String, TopSecretSplitRequestDto> satelliteDataMap = new HashMap<>();
    private ISatelliteRepository satelliteRepository;

    @Value("${satellites.size}")
    private String satellitesSize;

    @Autowired
    public SatelliteService(ISatelliteRepository satelliteRepository)
    {
        this.satelliteRepository = satelliteRepository;
    }

    @Override
    public void setSatelliteData(String satelliteName, TopSecretSplitRequestDto request) {
        satelliteDataMap.put(satelliteName, request);
    }

    @Override
    public Map<String, TopSecretSplitRequestDto> getSatelliteData() {
        return satelliteDataMap;
    }

    @Override
    public float[] getDistances()  {

         try {
             if (satelliteDataMap.size() < Integer.parseInt(satellitesSize))
                 throw new Exception("There is not enough information");

             float[] distances = new float[satelliteDataMap.size()];

             int index = 0;
             for (Map.Entry<String, TopSecretSplitRequestDto> entry :  satelliteDataMap.entrySet()) {
                 TopSecretSplitRequestDto value = entry.getValue();
                 distances[index] = value.getDistance();
                 index ++;
             }
             return distances;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float[][] getPositions()  {

        try {
            if (satelliteDataMap.size() < 3)
                throw new Exception("There is not enough information");

            float[][] positions = new float[satelliteDataMap.size()][2];

            int index = 0;
            for (Map.Entry<String, TopSecretSplitRequestDto> entry :  satelliteDataMap.entrySet()) {
                String key = entry.getKey();
                Satellite satellite = satelliteRepository.getSatellite(key);
                float[] position = new float[2];
                position[0] = satellite.getPosition().getX();
                position[1] = satellite.getPosition().getY();
                positions[index] = position;
                index ++;

            }
            return positions;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[][] getMessages() {
        try {
            int messageSize = 0;

            List<TopSecretSplitRequestDto> satelliteDataList = new ArrayList<>(satelliteDataMap.values());

            for (int i=0 ; i < satelliteDataList.size(); i++){
                if(satelliteDataList.get(i).getMessage().length > messageSize){
                    messageSize = satelliteDataList.get(i).getMessage().length;
                }
            }

            String[][] messages = new String[satelliteDataList.size()][messageSize];
            for (int i=0 ; i < satelliteDataList.size(); i++){
                messages[i] = satelliteDataList.get(i).getMessage();
            }

            return messages;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
