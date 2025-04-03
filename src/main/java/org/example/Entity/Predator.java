package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;

public class Predator extends Creature{

    private final int attackRate;

    public Predator(Coordinates coordinates) {
        super(coordinates, 1, 20);
        this.attackRate = 5;
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
