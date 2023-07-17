package com.challenge.meli.services.satellite;

import com.challenge.meli.dto.request.TopSecretRequestDto;

public interface ISatelliteService {
    public float[] getDistances(TopSecretRequestDto request) throws Exception;
    public String[][] getMessages(TopSecretRequestDto request);
}
