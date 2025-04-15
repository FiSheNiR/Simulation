package org.example.actions;

import org.example.entity.Creature;
import org.example.map.GameMap;

public class MoveEntityAction implements Action {
    @Override
    public void execute(GameMap gameMap) {
        gameMap.getEntities().values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList()
                .forEach(creature -> creature.makeMove(gameMap));
    }
}
