package com.challenge.meli.dto.response;

import com.challenge.meli.dto.response.PositionResponseDto;

public class TopSecretResponseDto {
    private PositionResponseDto position;
    private String message;

    public TopSecretResponseDto() {
        this.position = new PositionResponseDto();
    }

    public PositionResponseDto getPosition() {
        return position;
    }

    public void setPosition(PositionResponseDto position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}