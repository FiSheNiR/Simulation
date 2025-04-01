package org.example.Map;

import org.example.Entity.Entity;
import org.example.Simulation.Settings;

public class MapConsoleRenderer {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN_SQUARE_BACKGROUND = "\u001B[48;5;22m";


    public void render(GameMap gameMap) {

        System.out.println("+" + "---+".repeat(Settings.AMOUNT_OF_VERTICAL_COLUMNS));

        for (int horizontal = 0; horizontal <= Settings.AMOUNT_OF_HORIZONTAL_ROWS; horizontal++) {
            StringBuilder line = new StringBuilder("|");
            for (int vertical = 0; vertical <= Settings.AMOUNT_OF_VERTICAL_COLUMNS; vertical++) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                if (gameMap.isFieldEmpty(coordinates)) {
                    line.append(getSpriteForEmptyField());
                }
                else {
                    line.append(getEntitySprite(gameMap.getEntityByCoordinates(coordinates)));
                }

                line.append("|");
            }

            line.append(ANSI_RESET);
            System.out.println(line.toString());
            System.out.println("+" + "---+".repeat(Settings.AMOUNT_OF_VERTICAL_COLUMNS));
        }

    }

    private String getSpriteForEmptyField() {
        return (ANSI_GREEN_SQUARE_BACKGROUND + "    ");
    }

    private String selectEmojiForEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()){
            case "Herbivore" -> "\uD83D\uDC07";
            case "Predator" -> "\uD83D\uDC05";
            case "Obstacle" -> "\uD83E\uDEA8";
            case "Plant" -> "\uD83C\uDF31";
            default -> "";
        };
    }
    private String getEntitySprite(Entity entity) {
        return (ANSI_GREEN_SQUARE_BACKGROUND+ " " + selectEmojiForEntity(entity) + " ");
    }
}
