package org.example.Actions;

import org.example.Map.Map;

public class Context {
    private Action action;

    public void setAction(Action action) {
        this.action = action;
    }

    public void executeAction(Map map) {
        action.execute(map);
    }
}
