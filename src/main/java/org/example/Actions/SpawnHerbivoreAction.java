package org.example.Actions;

import org.example.Entity.Entity;
import org.example.Entity.Herbivore;
import org.example.Map.Coordinates;

public class SpawnHerbivoreAction extends SpawnEntityAction {

    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Herbivore(coordinates);
    }

}
