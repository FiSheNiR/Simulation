package org.example.Entity;

import org.example.Map.CoordinateShift;
import org.example.Map.Coordinates;
import org.example.Map.Map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Creature extends Entity {

    private final int speed;
    private final int health;
    private final Random random = new Random();

    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public Set<Coordinates> getAvailableMoveFields(Map map){
        Set<Coordinates> result = new HashSet<>();
        for (CoordinateShift shift : getCreatureMoves()){
            if (coordinates.canShift(shift)){
                Coordinates newCoordinates = coordinates.shift(shift);

                if (isFieldAvailableForMove(newCoordinates, map)){
                    result.add(newCoordinates);
                }

            }
        }
        return result;
    }

    protected Set<CoordinateShift> getCreatureMoves(){
        Set<CoordinateShift> result = new HashSet<>();

        for (int fileShift = -1; fileShift <= 1; fileShift++) {
            for (int rankShift = -1; rankShift <= 1; rankShift++) {
                if ((fileShift == 0) && (rankShift == 0)) {
                    continue;
                }
                result.add(new CoordinateShift(fileShift, rankShift));
            }
        }
        return result;
    }

    protected boolean isFieldAvailableForMove(Coordinates coordinates, Map map){
        return map.isFieldEmpty(coordinates) || !(map.getEntityByCoordinates(coordinates) instanceof Obstacle) ;
    }
    public Coordinates getRandomShiftCoordinates(Map map){
        Set<Coordinates> available = getAvailableMoveFields(map);
        ArrayList<Coordinates> possibleMoves = new ArrayList<>(available);
        int randomIndex = random.nextInt(available.size());
        return possibleMoves.get(randomIndex);
    }

    public abstract void makeMove(Map map);

}
