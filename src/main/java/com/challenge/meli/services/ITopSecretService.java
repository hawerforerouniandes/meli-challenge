package com.challenge.meli.services;

import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.dto.response.TopSecretResponseDto;
import java.util.concurrent.CompletableFuture;

public interface ITopSecretService {
    CompletableFuture<TopSecretResponseDto> addTopSecretData(TopSecretRequestDto request);
    void addSatelliteData(String satelliteName, TopSecretSplitRequestDto request);
    CompletableFuture<TopSecretResponseDto> getTopSecretData();
}
