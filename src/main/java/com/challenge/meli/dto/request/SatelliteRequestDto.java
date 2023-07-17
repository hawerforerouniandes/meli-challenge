package com.challenge.meli.dto.request;

public class SatelliteRequestDto {
    private String name;
    private float distance;
    private String[] message;

    public SatelliteRequestDto() {
    }

    public SatelliteRequestDto(String name, float distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }
}
