package com.challenge.meli.services;

import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.dto.response.TopSecretResponseDto;

public interface ITopSecretService {
    TopSecretResponseDto addTopSecretData(TopSecretRequestDto request);
    void addSatelliteData(String satelliteName, TopSecretSplitRequestDto request);
    TopSecretResponseDto getTopSecretData();
}
