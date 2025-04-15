package org.example.actions;

import org.example.entity.Creature;
import org.example.entity.Entity;
import org.example.entity.Herbivore;
import org.example.entity.Predator;
import org.example.map.Coordinates;
import org.example.map.GameMap;
import java.lang.reflect.Constructor;
import java.util.Random;

public class SpawnEntityAction implements Action {

    private int spawnRate = 1;
    private final Random rand = new Random();
    private final Class<? extends Entity> className;

    public SpawnEntityAction(Class<? extends Entity> className) {
        this.className = className;
    }

    public SpawnEntityAction(Class<? extends Entity> className, int spawnRate) {
        this.className = className;
        this.spawnRate = spawnRate;
    }

    @Override
    public void execute(GameMap gameMap) {
        int count;

        if (spawnRate>1){
            count = spawnRate;
        }else count = countEntities(className, gameMap);

        for (int i = 0; i < count; i++) {
            Coordinates emptyCell = getRandomEmptyCell(gameMap);

            if (className == Herbivore.class || className == Predator.class) {
                gameMap.setCreatures(emptyCell, (Creature) spawnCreature(className, emptyCell));}
            else {
                gameMap.setEntities(emptyCell, spawnEntity(className));
            }
        }
    }

    private <T extends Entity> T spawnCreature(Class<T> clazz, Coordinates coordinates) {
        try {
            Constructor<T> constructor = clazz.getConstructor(Coordinates.class);
            return constructor.newInstance(coordinates);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private <T extends Entity> T spawnEntity(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            return constructor.newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Coordinates getRandomEmptyCell(GameMap gameMap) {
        while (true) {
            int randomHorizontalPosition = rand.nextInt(gameMap.getGameMapHeight());
            int randomVerticalPosition = rand.nextInt(gameMap.getGameMapWidth());
            if (gameMap.isFieldEmpty(new Coordinates(randomHorizontalPosition, randomVerticalPosition))) {
                return new Coordinates(randomHorizontalPosition, randomVerticalPosition);
            }
        }
    }

    private int countEntities(Class<? extends Entity> className, GameMap gameMap) {
        int count = (int) gameMap.getEntities().values().stream()
                .filter(className::isInstance)
                .count();
        return Math.max(0, spawnRate - count);
    }
}
