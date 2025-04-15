package org.example.entity;

import org.example.map.Coordinates;
import org.example.map.GameMap;

public class Herbivore extends Creature{

    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }

    public Herbivore(Coordinates coordinates) {
        super(coordinates, 1, 10);
    }

    @Override
    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < speed; i++) {
            move(this.coordinates, gameMap, Herbivore.class);
        }
    }
}
