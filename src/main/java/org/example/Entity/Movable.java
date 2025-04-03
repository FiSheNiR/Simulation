package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;
import org.example.Service.BFS;

import java.util.Deque;

public interface Movable {

    default void move(Coordinates from, GameMap gameMap, Class<? extends Creature> creatureClass) {
        BFS bfs = new BFS(gameMap);

        // Поиск ближайшей цели, пути или случайного сдвига
        Coordinates targetNear = bfs.isTargetNear(from, creatureClass);
        Deque<Coordinates> path = bfs.findPathToTarget(from, creatureClass);
        Coordinates randomShift = bfs.getRandomShiftCoordinates(from);

        // Основная логика движения
        if (targetNear != null) {
            handleTargetNear(from, targetNear, gameMap, creatureClass);
        } else if (!path.isEmpty()) {
            travel(from, path.pollFirst(), gameMap);
        } else if (randomShift != null) {
            travel(from, randomShift, gameMap);
        } else {
            travel(from, from, gameMap); // Остаемся на месте
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
        Entity predator = gameMap.getEntityByCoordinates(predatorCoordinates);
        Entity target = gameMap.getEntityByCoordinates(targetCoordinates);

        if (predator instanceof Predator && target instanceof Herbivore) {
            Herbivore herbivore = (Herbivore) target;
            Predator predatorInstance = (Predator) predator;
            herbivore.setHealth(herbivore.getHealth() - predatorInstance.getAttackRate());
        }
    }

    private boolean isAliveAfterAttack(Coordinates predatorCoordinates, Coordinates targetCoordinates, GameMap gameMap) {
        Entity predator = gameMap.getEntityByCoordinates(predatorCoordinates);
        Entity target = gameMap.getEntityByCoordinates(targetCoordinates);

        if (predator instanceof Predator && target instanceof Herbivore) {
            Herbivore herbivore = (Herbivore) target;
            Predator predatorInstance = (Predator) predator;
            return herbivore.getHealth() - predatorInstance.getAttackRate() > 0;
        }
        return false;
    }

    private void travel(Coordinates from, Coordinates to, GameMap gameMap) {
        gameMap.moveEntity(from, to);
    }
}