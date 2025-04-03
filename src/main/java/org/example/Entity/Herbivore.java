package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;

public class Herbivore extends Creature{

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
