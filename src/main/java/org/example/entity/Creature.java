package org.example.entity;

import org.example.map.Coordinates;
import org.example.map.GameMap;

public abstract class Creature extends Entity implements Movable {

    public Coordinates coordinates;
    protected int speed;
    private int health;

    public Creature(Coordinates coordinates, int speed, int health) {
        this.coordinates = coordinates;
        this.speed = speed;
        this.health = health;
    }

    protected int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public abstract void makeMove(GameMap gameMap);
}
