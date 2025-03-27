package org.example;

import org.example.Actions.Action;
import org.example.Actions.Context;
import org.example.Actions.EntityStartPositionAction;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public Map map = new Map();
    public int moveCounter;
    public MapConsoleRenderer mapConsoleRenderer = new MapConsoleRenderer();
    public Context context = new Context();

    List<Action> initActions = new ArrayList<>();
    List<Context> turnActions = new ArrayList<>();


    public void startSimulation() {
        initActions.add(new EntityStartPositionAction());
        for (Action action : initActions) {
            context.setAction(action);
            context.executeAction(map);
        }

        mapConsoleRenderer.render(map);
    }
}
