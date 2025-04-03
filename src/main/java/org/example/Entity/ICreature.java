package org.example.Entity;
import org.example.Map.Coordinates;
import org.example.Map.GameMap;

public interface ICreature {

    default void travel(Coordinates from, Coordinates to, GameMap gameMap) {
        gameMap.moveEntity(from, to);
    }
}