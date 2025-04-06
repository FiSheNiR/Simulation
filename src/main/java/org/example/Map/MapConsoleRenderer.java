package org.example.Map;

import org.example.Entity.Entity;
import org.example.Simulation.Settings;

public class MapConsoleRenderer {

    public void render(GameMap gameMap) {

        System.out.println("+" + "-----+".repeat(Settings.AMOUNT_OF_VERTICAL_COLUMNS));

        for (int horizontal = Settings.HORIZONTAL_BOTTOM_BOUND; horizontal <= Settings.AMOUNT_OF_HORIZONTAL_ROWS; horizontal++) {
            StringBuilder line = new StringBuilder("|");
            for (int vertical = Settings.VERTICAL_BOTTOM_BOUND; vertical <= Settings.AMOUNT_OF_VERTICAL_COLUMNS; vertical++) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                if (gameMap.isFieldEmpty(coordinates)) {
                    line.append(getSpriteForEmptyField());
                }
                else {
                    line.append(getEntitySprite(gameMap.getEntityByCoordinates(coordinates)));
                }

                line.append("|");
            }

            line.append(Settings.ANSI_RESET);
            System.out.println(line);
            System.out.println("+" + "-----+".repeat(Settings.AMOUNT_OF_VERTICAL_COLUMNS));
        }

    }

    private String getSpriteForEmptyField() {
        return (Settings.ANSI_GREEN_SQUARE_BACKGROUND + "    ");
    }

    private String selectEmojiForEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()){
            case "Herbivore" -> Settings.HERBIVORE_EMOJI;
            case "Predator" -> Settings.PREDATOR_EMOJI;
            case "Obstacle" -> Settings.OBSTACLE_EMOJI;
            case "Plant" -> Settings.PLANT_EMOJI;
            default -> "";
        };
    }
    private String getEntitySprite(Entity entity) {
        return (Settings.ANSI_GREEN_SQUARE_BACKGROUND+ " " + selectEmojiForEntity(entity) + " ");
    }
}
