package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.Map;

public class Predator extends Creature{

    private final int attackRate;

    public Predator(Coordinates coordinates) {
        super(coordinates, 2, 20);
        this.attackRate = 5;
    }

    @Override
    protected boolean isFieldAvailableForMove(Coordinates coordinates, Map map) {

        boolean result = super.isFieldAvailableForMove(coordinates, map);
        Entity entity = map.getEntityByCoordinates(coordinates);

        if (result) {
            if (entity instanceof Predator || entity instanceof Plant) {
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void makeMove(Map map) {
        travel(map);
    }

    public void travel(Map map) {
        map.moveEntity(this.coordinates, getRandomShiftCoordinates(map));
    }

    public void attack(){}
}
