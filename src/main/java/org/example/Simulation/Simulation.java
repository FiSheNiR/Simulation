package org.example.Simulation;

import org.example.Actions.*;
import org.example.Entity.*;
import org.example.Map.GameMap;
import org.example.Map.MapConsoleRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final GameMap gameMap = new GameMap();
    public int moveCounter = 0;
    private final MapConsoleRenderer mapConsoleRenderer = new MapConsoleRenderer();

    List<Action> initActions = new ArrayList<>();
    List<Action> turnActions = new ArrayList<>();

    public void startSimulation() {
        createActions();
        for (Action action : initActions) {
            action.execute(gameMap);
        }
        while (moveCounter < 100) {
            System.out.println("Ход номер: " + moveCounter);
            nextTurn();
            moveCounter++;
        }

    }

    private void nextTurn(){
        try {
            for (Action action : turnActions) {
                action.execute(gameMap);
            }
            turnActions.removeLast();
            turnActions.removeLast();
            turnActions.add(new SpawnEntityAction(Herbivore.class, countEntities(Herbivore.class)));
            turnActions.add(new SpawnEntityAction(Plant.class, countEntities(Plant.class)));
            Thread.sleep(Settings.TIME_SLEEP_BETWEEN_TURNS);
            mapConsoleRenderer.render(gameMap);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createActions(){
        initActions.add(new SpawnEntityAction(Obstacle.class));
        initActions.add(new SpawnEntityAction(Predator.class));
        turnActions.add(new MoveEntityAction());
        turnActions.add(new SpawnEntityAction(Herbivore.class, countEntities(Herbivore.class)));
        turnActions.add(new SpawnEntityAction(Plant.class, countEntities(Plant.class)));
    }

    private int countEntities(Class<? extends Entity> className) {
        int count = (int) gameMap.getCurrentGameMap().values().stream()
                .filter(className::isInstance)
                .count();
        return Math.max(0, Settings.BASE_SPAWN_RATE - count);
    }
}
