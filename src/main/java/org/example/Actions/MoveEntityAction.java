package org.example.Actions;

import org.example.Entity.Creature;
import org.example.Map.GameMap;

public class MoveEntityAction implements Action {
    @Override
    public void execute(GameMap gameMap) {
        gameMap.getCurrentGameMap().values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList()
                .forEach(creature -> creature.makeMove(gameMap));
    }
}
