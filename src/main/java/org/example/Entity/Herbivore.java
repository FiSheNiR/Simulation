package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;

public class Herbivore extends Creature{

    public Herbivore(Coordinates coordinates) {
        super(coordinates, 1, 10);
    }

    @Override
    public boolean isFieldAvailableForMove(Coordinates coordinates, GameMap gameMap) {
        boolean result = super.isFieldAvailableForMove(coordinates, gameMap);
        Entity entity = gameMap.getEntityByCoordinates(coordinates);
        if (result) {
            return !(entity instanceof Predator) && !(entity instanceof Herbivore);
        }
        return false;
    }

    @Override
    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < speed; i++) {
            BFS bfs = new BFS(gameMap);
            if(bfs.isGrassNear(this.coordinates)!= null){
                travel(this.coordinates, bfs.isGrassNear(this.coordinates),gameMap);
            } else if (!bfs.findPathToGrass(this.coordinates).isEmpty()) {
                travel(this.coordinates, bfs.findPathToGrass(coordinates).getFirst(), gameMap);
            } else {
                travel(this.coordinates, getRandomShiftCoordinates(gameMap, this.coordinates), gameMap);
            }
        }
    }
}
