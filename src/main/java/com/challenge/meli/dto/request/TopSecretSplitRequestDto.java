package com.challenge.meli.dto.request;

public class TopSecretSplitRequestDto {
    private float distance;
    private String[] message;

    public TopSecretSplitRequestDto() {
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
