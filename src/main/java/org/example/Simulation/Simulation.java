package org.example.Simulation;

import org.example.Actions.*;
import org.example.Entity.Herbivore;
import org.example.Entity.Obstacle;
import org.example.Entity.Plant;
import org.example.Entity.Predator;
import org.example.Map.Coordinates;
import org.example.Map.Map;
import org.example.Map.MapConsoleRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Simulation {
    private final Map map = new Map();
    public int moveCounter = 0;
    private final MapConsoleRenderer mapConsoleRenderer = new MapConsoleRenderer();

    List<Action> initActions = new ArrayList<>();
    List<Action> turnActions = new ArrayList<>();


    public void startSimulation() {
        createActions();
        for (Action action : initActions) {
            action.execute(map);
        }
        while (moveCounter < 100) {
            nextTurn();
            int i = 123;
            moveCounter++;
        }

    }

    private void nextTurn(){
        try {
            for (Action action : turnActions) {
                action.execute(map);
            }
            mapConsoleRenderer.render(map);

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createActions(){
        initActions.add(new SpawnEntityAction(Obstacle.class));
        initActions.add(new SpawnEntityAction(Herbivore.class));
        initActions.add(new SpawnEntityAction(Predator.class));
        initActions.add(new SpawnEntityAction(Plant.class));
        turnActions.add(new MakeEntityMoveAction());
    }
}
