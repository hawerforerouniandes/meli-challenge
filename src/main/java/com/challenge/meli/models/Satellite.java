package com.challenge.meli.models;

import org.springframework.boot.context.properties.bind.ConstructorBinding;

public class Satellite {
    private String name;
    private Position position;

    public Satellite() {
    }

    public Satellite(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

}
