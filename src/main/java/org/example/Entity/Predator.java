package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;
import org.example.Simulation.Settings;

public class Predator extends Creature{

    private final int attackRate;

    public Predator(Coordinates coordinates) {
        super(coordinates, Settings.BASE_PREDATOR_SPEED_RATE, Settings.BASE_HEALTH);
        this.attackRate = Settings.BASE_ATTACK_RATE;
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
