package org.example.entity;

import org.example.map.Coordinates;
import org.example.map.GameMap;

public class Predator extends Creature{

    private int attackRate = 5;

    public Predator(Coordinates coordinates, int speed, int health, int attackRate) {
        super(coordinates, speed, health);
        this.attackRate = attackRate;
    }

    public Predator(Coordinates coordinates) {
        super(coordinates, 1, 10);
    }

    public int getAttackRate() {
        return attackRate;
    }

    @Override
    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < speed; i++) {
            move(this.coordinates, gameMap, Predator.class);
        }
    }
}
