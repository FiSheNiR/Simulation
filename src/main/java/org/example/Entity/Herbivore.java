package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;
import org.example.Service.BFS;

import java.util.Deque;

public class Herbivore extends Creature{

    public Herbivore(Coordinates coordinates) {
        super(coordinates, 1, 10);
    }

    @Override
    public void makeMove(GameMap gameMap) {
        BFS bfs = new BFS(gameMap);

        for (int i = 0; i < speed; i++) {
            Coordinates targetNear = bfs.isTargetNear(this.coordinates, Herbivore.class);
            if (targetNear != null) {
                travel(this.coordinates, targetNear, gameMap);
                continue;
            }

            Deque<Coordinates> path = bfs.findPathToTarget(this.coordinates, Herbivore.class);
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
