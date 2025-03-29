package org.example.Actions;

import org.example.Entity.Entity;
import org.example.Entity.Herbivore;
import org.example.Entity.Obstacle;
import org.example.Map.Coordinates;
import org.example.Map.Map;
import org.example.Simulation.Settings;

import java.lang.reflect.Constructor;
import java.util.Random;

public class SpawnEntityByReflection implements Action {

    protected int spawnRate = 5;
    private final Random rand = new Random();
    private final Class<? extends Entity> className;

    public SpawnEntityByReflection(Class<? extends Entity> className) {
        this.className = className;
    }

    @Override
    public void execute(Map map) {
        for (int i = 0; i < spawnRate; i++) {
            Coordinates emptyCell = getRandomEmptyCell(map);
            map.setEntities(emptyCell, spawnEntity(className, emptyCell));
        }
    }

    private Coordinates getRandomEmptyCell(Map map) {
        while (true) {
            int randomHorizontalPosition = rand.nextInt(Settings.AMOUNT_OF_HORIZONTAL_ROWS);
            int randomVerticalPosition = rand.nextInt(Settings.AMOUNT_OF_VERTICAL_COLUMNS);
            if (map.isFieldEmpty(new Coordinates(randomHorizontalPosition, randomVerticalPosition))) {
                return new Coordinates(randomHorizontalPosition, randomVerticalPosition);
            }
        }
    }

    private <T extends Entity> T spawnEntity(Class<T> clazz, Coordinates coordinates) {
        try {

            Constructor<T> constructor = clazz.getConstructor(Coordinates.class);
            return constructor.newInstance(coordinates);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
