package org.example.Entity;

import org.example.Coordinates;

public abstract class Entity {

    public Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
