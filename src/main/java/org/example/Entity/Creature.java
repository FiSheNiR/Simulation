package org.example.Entity;

import org.example.Map.Coordinates;

public abstract class Creature extends Entity {

    private final int speed;
    private final int health;

    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove();
}
