package org.example.Entity;

import org.example.Map.CoordinateShift;
import org.example.Map.Coordinates;
import org.example.Map.GameMap;

import java.util.*;

public interface Movable {

    Random random = new Random();

    default List<Coordinates> getAvailableMoveFields(GameMap gameMap, Coordinates coordinates) {
        List<Coordinates> result = new ArrayList<>();
        for (CoordinateShift shift : getCreatureMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if (isFieldAvailableForMove(newCoordinates, gameMap)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    default Set<CoordinateShift> getCreatureMoves() {
        Set<CoordinateShift> result = new HashSet<>();

//        for (int fileShift = -1; fileShift <= 1; fileShift++) {
//            for (int rankShift = -1; rankShift <= 1; rankShift++) {
//                if ((fileShift == 0) && (rankShift == 0)) {
//                    continue;
//                }
//                result.add(new CoordinateShift(fileShift, rankShift));
//            }
//        }

        result.add(new CoordinateShift(1,0));

        result.add(new CoordinateShift(-1,0));

        result.add(new CoordinateShift(0,1));

        result.add(new CoordinateShift(0,-1));

        return result;
    }

    default boolean isFieldAvailableForMove(Coordinates coordinates, GameMap gameMap) {
        if (gameMap.isFieldEmpty(coordinates)){
            return true;
        }
        return !(gameMap.getEntityByCoordinates(coordinates) instanceof Obstacle);
    }

    default Coordinates getRandomShiftCoordinates(GameMap gameMap, Coordinates coordinates) {
        List<Coordinates> available = getAvailableMoveFields(gameMap, coordinates);
        ArrayList<Coordinates> possibleMoves = new ArrayList<>(available);
        int randomIndex = random.nextInt(available.size());
        return possibleMoves.get(randomIndex);
    }

    default void travel(Coordinates from, Coordinates to, GameMap gameMap) {
        gameMap.moveEntity(from, to);
    }
}