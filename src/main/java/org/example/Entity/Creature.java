package org.example.Entity;

import org.example.Coordinates;

public abstract class Creature extends Entity {

    public int speed;
    public int health;

    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove();
}
