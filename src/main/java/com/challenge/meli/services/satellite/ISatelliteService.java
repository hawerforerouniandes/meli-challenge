package com.challenge.meli.services.satellite;

import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.request.TopSecretSplitRequestDto;

import java.util.Map;

public interface ISatelliteService {

    public void setSatelliteData(String satelliteName, TopSecretSplitRequestDto request);
    public Map<String, TopSecretSplitRequestDto> getSatelliteData();
    public float[] getDistances();
    public float[][] getPositions();
    public String[][] getMessages();
}

