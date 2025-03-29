package org.example.Actions;

import org.example.Entity.Entity;
import org.example.Entity.Plant;
import org.example.Map.Coordinates;

public class SpawnPlantAction extends SpawnEntityAction{

    public SpawnPlantAction() {
        super.spawnRate = 10;
    }

    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Plant(coordinates);
    }
}
