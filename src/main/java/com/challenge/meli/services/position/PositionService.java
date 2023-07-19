package com.challenge.meli.services.position;

import com.challenge.meli.repositories.ISatelliteRepository;
import com.challenge.meli.utils.InverseDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionService implements IPositionService{

    @Autowired
    public PositionService() {

    }

    @Override
    public float[] getLocation(float[] distances, float[][] positions){

        try {
            if (distances.length != positions.length)
                throw new Exception("Distances and positions do not match");

            return InverseDistance.location(distances, positions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

