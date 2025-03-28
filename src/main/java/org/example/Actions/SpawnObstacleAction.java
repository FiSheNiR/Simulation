package org.example.Actions;

import org.example.Entity.Entity;
import org.example.Entity.Obstacle;
import org.example.Map.Coordinates;

public class SpawnObstacleAction extends SpawnEntityAction{
    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Obstacle(coordinates);
    }
}
