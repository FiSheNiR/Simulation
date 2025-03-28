package org.example.Actions;

import org.example.Entity.Entity;
import org.example.Entity.Predator;
import org.example.Map.Coordinates;

public class SpawnPredatorAction extends SpawnEntityAction{
    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Predator(coordinates);
    }
}
