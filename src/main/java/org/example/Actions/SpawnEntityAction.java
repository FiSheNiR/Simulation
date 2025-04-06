package org.example.Actions;

import org.example.Entity.Entity;
import org.example.Map.Coordinates;
import org.example.Map.GameMap;
import org.example.Simulation.Settings;
import java.lang.reflect.Constructor;
import java.util.Random;

public class SpawnEntityAction implements Action {

    private int spawnRate;
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

        if (spawnRate>0){
            count = spawnRate;
        }else count = countEntities(className, gameMap);

        for (int i = 0; i < count; i++) {
            Coordinates emptyCell = getRandomEmptyCell(gameMap);
            gameMap.setEntities(emptyCell, spawnEntity(className, emptyCell));
        }
    }

    private Coordinates getRandomEmptyCell(GameMap gameMap) {
        while (true) {
            int randomHorizontalPosition = rand.nextInt(Settings.AMOUNT_OF_HORIZONTAL_ROWS);
            int randomVerticalPosition = rand.nextInt(Settings.AMOUNT_OF_VERTICAL_COLUMNS);
            if (gameMap.isFieldEmpty(new Coordinates(randomHorizontalPosition, randomVerticalPosition))) {
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

    private int countEntities(Class<? extends Entity> className, GameMap gameMap) {
        int count = (int) gameMap.getCurrentGameMap().values().stream()
                .filter(className::isInstance)
                .count();
        return Math.max(0, Settings.BASE_SPAWN_RATE - count);
    }
}
