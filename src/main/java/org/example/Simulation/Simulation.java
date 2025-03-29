package org.example.Simulation;

import org.example.Actions.*;
import org.example.Entity.Herbivore;
import org.example.Entity.Obstacle;
import org.example.Entity.Plant;
import org.example.Entity.Predator;
import org.example.Map.Map;
import org.example.Map.MapConsoleRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final Map map = new Map();
    public int moveCounter;
    private final MapConsoleRenderer mapConsoleRenderer = new MapConsoleRenderer();

    List<Action> initActions = new ArrayList<>();
    List<Action> turnActions = new ArrayList<>();


    public void startSimulation() {
        createActions();
        for (Action action : initActions) {
            action.execute(map);
        }

        mapConsoleRenderer.render(map);
    }

    public void createActions(){

//        initActions.add(new SpawnPredatorAction());
//        initActions.add(new SpawnObstacleAction());
//        initActions.add(new SpawnPlantAction());
//        initActions.add(new SpawnHerbivoreAction());
        initActions.add(new SpawnEntityByReflection(Obstacle.class));
        initActions.add(new SpawnEntityByReflection(Herbivore.class));
        initActions.add(new SpawnEntityByReflection(Predator.class));
        initActions.add(new SpawnEntityByReflection(Plant.class));

//   initActions.add(new SpawnEntityAction());
    }
}
