package org.example.Actions;
import org.example.Map.Coordinates;
import org.example.Entity.*;
import org.example.Map.Map;
import org.example.Simulation.Settings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class SpawnEntityAction implements Action {

    protected int spawnRate;
    private final Random rand = new Random();

    @Override
    public void execute(Map map) {
        for (int i = 0; i < spawnRate; i++) {
            Coordinates emptyCell = getRandomEmptyCell(map);
            map.setEntities(emptyCell, spawnEntity(emptyCell));
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

    public abstract Entity spawnEntity(Coordinates coordinates);

    // спросить у гпт как + рефлексия <? extends entity>
}
