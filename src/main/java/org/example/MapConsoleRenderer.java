package org.example;

import org.example.Entity.Entity;

public class MapConsoleRenderer {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN_SQUARE_BACKGROUND = "\u001B[48;5;22m";


    public void render(Map map) {

        System.out.println("+" + "---+".repeat(MapScope.amountOfVerticalColumns));

        for (int horizontal = 0; horizontal < MapScope.amountOfHorizontalRows; horizontal++) {
            StringBuilder line = new StringBuilder("|");
            for (int vertical = 0; vertical < MapScope.amountOfVerticalColumns; vertical++) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                if (map.isFieldEmpty(coordinates)) {
                    line.append(getSpriteForEmptyField());
                }
                else {
                    line.append(getEntitySprite(map.getEntity(coordinates)));
                }

                line.append("|");
            }

            line.append(ANSI_RESET);
            System.out.println(line.toString());
            System.out.println("+" + "---+".repeat(MapScope.amountOfVerticalColumns));
        }

    }

    private String getSpriteForEmptyField() {
        return (ANSI_GREEN_SQUARE_BACKGROUND + "    ");
    }

    private String selectEmojiForEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()){
            case "Herbivore" -> "\uD83D\uDC07";
            case "Predator" -> "\uD83D\uDC05";
            case "Obstacle" -> "\uD83C\uDF33";
            case "Plant" -> "\uD83C\uDF31";
            default -> "";
        };
    }
    private String getEntitySprite(Entity entity) {
        return (ANSI_GREEN_SQUARE_BACKGROUND+ " " + selectEmojiForEntity(entity) + " ");
    }
}
