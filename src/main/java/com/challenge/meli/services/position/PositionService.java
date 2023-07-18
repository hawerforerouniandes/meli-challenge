package com.challenge.meli.services.position;

import com.challenge.meli.repositories.ISatelliteRepository;
import com.challenge.meli.utils.InverseDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionService implements IPositionService{

    private ISatelliteRepository satelliteRepository;

    @Autowired
    public PositionService(ISatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    @Override
    public float[] getLocation(float[] distances, float[][] positions){

        try {
            return InverseDistance.location(distances, positions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

