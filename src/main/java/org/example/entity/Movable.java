package org.example.entity;

import org.example.map.Coordinates;
import org.example.map.GameMap;
import org.example.service.BFS;

import java.util.Deque;

public interface Movable {

    default void move(Coordinates from, GameMap gameMap, Class<? extends Creature> creatureClass) {
        BFS bfs = new BFS(gameMap);

        Coordinates targetNear = bfs.isTargetNear(from, creatureClass);
        Deque<Coordinates> path = bfs.findPathToTarget(from, creatureClass);
        Coordinates randomShift = bfs.getRandomShiftCoordinates(from);

        if (targetNear != null) {
            handleTargetNear(from, targetNear, gameMap, creatureClass);
        } else if (!path.isEmpty()) {
            travel(from, path.pollFirst(), gameMap);
        } else if (randomShift != null) {
            travel(from, randomShift, gameMap);
        } else {
            travel(from, from, gameMap);
        }
    }

    private void handleTargetNear(Coordinates from, Coordinates to, GameMap gameMap, Class<? extends Creature> creatureClass) {
        if (creatureClass == Predator.class && isAliveAfterAttack(from, to, gameMap)) {
            performAttack(from, to, gameMap);
        } else {
            travel(from, to, gameMap);
        }
    }

    private void performAttack(Coordinates predatorCoordinates, Coordinates targetCoordinates, GameMap gameMap) {
        Entity predator = gameMap.getEntity(predatorCoordinates);
        Entity target = gameMap.getEntity(targetCoordinates);

        if (predator instanceof Predator predatorInstance && target instanceof Herbivore herbivore) {
            herbivore.setHealth(herbivore.getHealth() - predatorInstance.getAttackRate());
        }
    }

    private boolean isAliveAfterAttack(Coordinates predatorCoordinates, Coordinates targetCoordinates, GameMap gameMap) {
        Entity predator = gameMap.getEntity(predatorCoordinates);
        Entity target = gameMap.getEntity(targetCoordinates);

        if (predator instanceof Predator predatorInstance && target instanceof Herbivore herbivore) {
            return herbivore.getHealth() - predatorInstance.getAttackRate() > 0;
        }
        return false;
    }

    private void travel(Coordinates from, Coordinates to, GameMap gameMap) {
        if (gameMap.getEntity(from) == null) {
            return;
        }
        gameMap.moveEntity(from, to);
    }
}