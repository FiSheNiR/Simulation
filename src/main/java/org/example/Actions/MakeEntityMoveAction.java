package org.example.Actions;

import org.example.Entity.Creature;
import org.example.Entity.Entity;
import org.example.Map.Coordinates;
import org.example.Map.Map;

import java.util.HashMap;
import java.util.Random;

public class MakeEntityMoveAction implements Action {

    @Override
    public void execute(Map map) {
        //Проходим по мапе получаем всех Creature
        //и у каждого вызываем make move
        HashMap<Coordinates, Entity> clonedMap = new HashMap<>(map.getCurrentGameMap());
        for (Entity entity : clonedMap.values()){
            if (entity instanceof Creature){
                ((Creature) entity).makeMove(map);
            }
        }

    }


}
