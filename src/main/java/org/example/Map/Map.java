package org.example.Map;

import org.example.Entity.Entity;
import org.example.Entity.Herbivore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();

    public void setEntities(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntityByCoordinates(from);

        removeEntity(from);

        setEntities(to, entity);
    }

    public Entity getEntityByCoordinates(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public HashMap<Coordinates, Entity> getCurrentGameMap() {
        return entities;
    }

    public boolean isFieldEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public void setupDefaultEntitiesPositions(){
        setEntities(new Coordinates(1, 1), new Herbivore(new Coordinates(1, 1)));
    }
}
