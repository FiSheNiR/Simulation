package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;

public class Predator extends Creature{

    private final int attackRate;

    public Predator(Coordinates coordinates) {
        super(coordinates, 1, 20);
        this.attackRate = 5;
    }

    @Override
    public boolean isFieldAvailableForMove(Coordinates coordinates, GameMap gameMap) {

        boolean result = super.isFieldAvailableForMove(coordinates, gameMap);
        Entity entity = gameMap.getEntityByCoordinates(coordinates);

        if (result) {
            return !(entity instanceof Predator) && !(entity instanceof Plant);
        }
        return false;
    }

    @Override
    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < speed; i++) {
            BFS bfs = new BFS(gameMap);
            if(bfs.isHerbivoreNear(this.coordinates)!= null){
                travel(this.coordinates, bfs.isHerbivoreNear(this.coordinates),gameMap);
            } else if (!bfs.findPathToHerbivore(this.coordinates).isEmpty()) {
                travel(this.coordinates, bfs.findPathToHerbivore(coordinates).getFirst(), gameMap);
            } else {
                travel(this.coordinates, getRandomShiftCoordinates(gameMap, this.coordinates), gameMap);
            }
        }
    }
}
