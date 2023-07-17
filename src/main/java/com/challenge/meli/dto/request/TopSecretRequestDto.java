package com.challenge.meli.dto.request;

import com.challenge.meli.dto.request.SatelliteRequestDto;

import java.util.List;

public class TopSecretRequestDto {
    private List<SatelliteRequestDto> satellites;

    public List<SatelliteRequestDto> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<SatelliteRequestDto> satellites) {
        this.satellites = satellites;
    }
}