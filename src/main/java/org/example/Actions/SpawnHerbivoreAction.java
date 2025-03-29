package org.example.Actions;

import org.example.Entity.Entity;
import org.example.Entity.Herbivore;
import org.example.Map.Coordinates;
import org.example.Map.Map;
import org.example.Simulation.Settings;

public class SpawnHerbivoreAction extends SpawnEntityAction {

    public SpawnHerbivoreAction() {
        super.spawnRate = 5;
    }


    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Herbivore(coordinates);
    }

}
