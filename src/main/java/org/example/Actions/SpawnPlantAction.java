package org.example.Actions;

import org.example.Entity.Entity;
import org.example.Entity.Plant;
import org.example.Map.Coordinates;

public class SpawnPlantAction extends SpawnEntityAction{
    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Plant(coordinates);
    }
}
