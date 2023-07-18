package com.challenge.meli.repositories;

import com.challenge.meli.models.Satellite;

import java.util.List;

public interface ISatelliteRepository {
    public List<Satellite> getAllSatellites();
    public Satellite getSatellite(String name);
}
