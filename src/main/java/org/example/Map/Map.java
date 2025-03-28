package org.example.Map;

import org.example.Entity.Entity;
import org.example.Entity.Herbivore;

import java.util.HashMap;

public class Map {

    public HashMap<Coordinates, Entity> entities = new HashMap<>();

    public void setEntities(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);

        removeEntity(from);

        setEntities(to, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public boolean isFieldEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public void setupDefaultEntitiesPositions(){
        setEntities(new Coordinates(1, 1), new Herbivore(new Coordinates(1, 1)));
    }
}
