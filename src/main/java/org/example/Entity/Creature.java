package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;

public abstract class Creature extends Entity implements Movable {

    protected final int speed;
    protected int health;

    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
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
