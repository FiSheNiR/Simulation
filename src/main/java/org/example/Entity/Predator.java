package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;
import org.example.Service.BFS;

import java.util.Deque;

public class Predator extends Creature{

    private final int attackRate;

    public Predator(Coordinates coordinates) {
        super(coordinates, 1, 20);
        this.attackRate = 5;
    }

    @Override
    public void makeMove(GameMap gameMap) {
        BFS bfs = new BFS(gameMap);

        for (int i = 0; i < speed; i++) {
            Coordinates targetNear = bfs.isTargetNear(this.coordinates, Predator.class);
            if (targetNear != null) {
                travel(this.coordinates, targetNear, gameMap);
                continue;
            }

            Deque<Coordinates> path = bfs.findPathToTarget(this.coordinates, Predator.class);
            if (!path.isEmpty()) {
                travel(this.coordinates, path.pollFirst(), gameMap);
                continue;
            }

            Coordinates randomShift = bfs.getRandomShiftCoordinates(this.coordinates);
            if (randomShift != null) {
                travel(this.coordinates, randomShift, gameMap);
                continue;
            }

            break;
        }
    }
}
