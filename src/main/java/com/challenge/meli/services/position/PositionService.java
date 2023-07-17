package com.challenge.meli.services.position;

import com.challenge.meli.models.Satellite;
import com.challenge.meli.repositories.satellite.ISatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionService implements IPositionService{

    private ISatelliteRepository satelliteRepository;

    @Autowired
    public PositionService(ISatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    @Override
    public float[] getLocation(float... distances){

        try {
            List<Satellite> satellites = satelliteRepository.getAllSatellites();

            float xSum = 0;
            float ySum = 0;
            float totalWeight = 0;

            for (int i = 0; i < distances.length; i++) {
                Satellite satellite = satellites.get(i);
                float weight = 1 / (float) Math.pow(distances[i], 2);
                xSum += satellite.getPosition().getX() * weight;
                ySum += satellite.getPosition().getY() * weight;
                totalWeight += weight;
            }

            float x = xSum / totalWeight;
            float y = ySum / totalWeight;

            return new float[]{x, y};
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

