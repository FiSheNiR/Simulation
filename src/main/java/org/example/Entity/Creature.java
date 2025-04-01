package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;

public abstract class Creature extends Entity implements Movable {

    protected final int speed;
    protected final int health;

    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove(GameMap gameMap);
}
