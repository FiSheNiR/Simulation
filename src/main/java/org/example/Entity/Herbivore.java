package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;
import org.example.Simulation.Settings;

public class Herbivore extends Creature{

    public Herbivore(Coordinates coordinates) {
        super(coordinates, Settings.BASE_HERBIVORE_SPEED_RATE, Settings.BASE_HEALTH);
    }

    @Override
    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < speed; i++) {
            move(this.coordinates, gameMap, Herbivore.class);
        }
    }
}
