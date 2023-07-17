package com.challenge.meli.services.position;

import com.challenge.meli.models.Satellite;
import com.challenge.meli.repositories.satellite.ISatelliteRepository;
import com.challenge.meli.utils.Trilateration;
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

            float[][] positions = new float[satellites.size()][2];

            for (int i = 0; i < satellites.size(); i++) {
                Satellite satellite = satellites.get(i);
                float[] position = new float[2];
                position[0] = satellite.getPosition().getX();
                position[1] = satellite.getPosition().getY();
                positions[i] = position;
            }

            return Trilateration.location(distances, positions);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

