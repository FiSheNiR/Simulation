package org.example.map;

import org.example.entity.Creature;
import org.example.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class GameMap {

    private final Map<Coordinates, Entity> entities = new HashMap<>();
    private final int gameMapWidth;
    private final int gameMapHeight;

    public GameMap(int gameMapWidth, int gameMapHeight) {
        this.gameMapWidth = gameMapWidth;
        this.gameMapHeight = gameMapHeight;
    }

    public int getGameMapHeight() {
        return gameMapHeight;
    }

    public int getGameMapWidth() {
        return gameMapWidth;
    }

    public void setCreatures(Coordinates coordinates, Creature creature) {
        creature.coordinates = coordinates;
        entities.put(coordinates, creature);
    }

    public void setEntities(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    private void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Creature entity = (Creature) getEntity(from);
        if (entity == null) {
            throw new IllegalStateException("No entity found at coordinates: " + from);
        }
        removeEntity(from);

        setCreatures(to, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public Map<Coordinates, Entity> getEntities() {
        return entities;
    }

    public boolean isFieldEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }
}
