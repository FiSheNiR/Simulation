package org.example.Simulation;

import org.example.Actions.*;
import org.example.Map.Map;
import org.example.Map.MapConsoleRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public Map map = new Map();
    public int moveCounter;
    public MapConsoleRenderer mapConsoleRenderer = new MapConsoleRenderer();
    public Context context = new Context();

    List<Action> initActions = new ArrayList<>();
    List<Action> turnActions = new ArrayList<>();


    public void startSimulation() {
        createActions();
        for (Action action : initActions) {
            context.setAction(action);
            context.executeAction(map);
        }

        mapConsoleRenderer.render(map);
    }

    public void createActions(){
        initActions.add(new SpawnHerbivoreAction());
        initActions.add(new SpawnPredatorAction());
        initActions.add(new SpawnObstacleAction());
        initActions.add(new SpawnPlantAction());
    }
}
