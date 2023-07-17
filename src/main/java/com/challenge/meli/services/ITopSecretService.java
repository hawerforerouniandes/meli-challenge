package com.challenge.meli.services;

import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.response.TopSecretResponseDto;

public interface ITopSecretService {
    TopSecretResponseDto topSecret(TopSecretRequestDto dto);
}
