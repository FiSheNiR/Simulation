package org.example.Actions;
import org.example.Coordinates;
import org.example.Entity.*;
import org.example.Map;
import org.example.MapConsoleRenderer;
import org.example.MapScope;

import java.util.Random;

public class EntityStartPositionAction implements Action {

    Random rand = new Random();

    @Override
    public void execute(Map map) {


        for (int horizontal = 0; horizontal < MapScope.amountOfHorizontalRows; horizontal++) {
            for (int vertical = 0; vertical < MapScope.amountOfVerticalColumns; vertical++) {
                int emptyCell = rand.nextInt(3);
                if (emptyCell > 0){
                    continue;
                }
                map.setEntities(new Coordinates(horizontal,vertical), getRandomEntity(horizontal, vertical));
            }
        }
    }

    private Entity getRandomEntity(int horizontal, int vertical) {
        int randomEntity = rand.nextInt(4);

        return switch (randomEntity){
            case 0 -> new Herbivore(new Coordinates(horizontal,vertical));
            case 1 -> new Predator(new Coordinates(horizontal,vertical));
            case 2 -> new Plant(new Coordinates(horizontal,vertical));
            default -> new Obstacle(new Coordinates(horizontal,vertical));
        };
    }
}
