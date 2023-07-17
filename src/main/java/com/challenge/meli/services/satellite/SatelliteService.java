package com.challenge.meli.services.satellite;

import com.challenge.meli.dto.request.TopSecretRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SatelliteService implements ISatelliteService{

    @Override
    public float[] getDistances(TopSecretRequestDto request)  {
        try {
            float[] distances = new float[request.getSatellites().size()];
            for (int i=0 ; i < request.getSatellites().size(); i++) {
                float distance = request.getSatellites().get(i).getDistance();
                if (distance <= 0)
                    throw new Exception("Distances to emitter not found");
                distances[i] = distance;
            }
            return distances;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[][] getMessages(TopSecretRequestDto request) {
        try {
            int messageSize = 0;

            for (int i=0 ; i < request.getSatellites().size(); i++){
                if(request.getSatellites().get(i).getMessage().length > messageSize){
                    messageSize = request.getSatellites().get(i).getMessage().length;
                }
            }

            String[][] messages = new String[request.getSatellites().size()][messageSize];
            for (int i=0 ; i < request.getSatellites().size(); i++){
                messages[i] = request.getSatellites().get(i).getMessage();
            }

            return messages;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
