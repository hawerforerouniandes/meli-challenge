package com.challenge.meli.repositories;

import com.challenge.meli.models.Position;
import com.challenge.meli.models.Satellite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SatelliteRepository implements ISatelliteRepository{

    @Autowired
    private Environment environment;

    @Override
    public List<Satellite> getAllSatellites() {
        try {
            List<Satellite> satellites = new ArrayList<>();
            int satellitesSize = Integer.parseInt(environment.getProperty("satellites.size"));

            for (int i = 0; i < satellitesSize; i++) {

                String name = environment.getProperty("satellites[" + i + "].name");
                int x = environment.getProperty("satellites[" + i + "].positionX", Integer.class);
                int y = environment.getProperty("satellites[" + i + "].positionY", Integer.class);

                Position position = new Position(x,y);
                Satellite satellite =new Satellite(name , position);

                satellites.add(satellite);

            }
            return satellites;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Satellite getSatellite(String key) {
        try {
            Satellite satellite = new Satellite();

            int satellitesSize = Integer.parseInt(environment.getProperty("satellites.size"));

            for (int i = 0; i < satellitesSize; i++) {

                String name = environment.getProperty("satellites[" + i + "].name");
                int x = environment.getProperty("satellites[" + i + "].positionX", Integer.class);
                int y = environment.getProperty("satellites[" + i + "].positionY", Integer.class);

                Position position = new Position(x,y);

                if(key.equals(name)){
                    satellite = new Satellite(name , position);
                    break;
                }
            }
            return satellite;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
