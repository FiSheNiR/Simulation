package org.example.Map;

import org.example.Entity.Entity;

import java.util.HashMap;

public class GameMap {

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();

    public void setEntities(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    private void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntityByCoordinates(from);
        if (entity == null) {
            throw new IllegalStateException("No entity found at coordinates: " + from);
        }
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
}
