package org.example.map;

import org.example.entity.Entity;

public class MapConsoleRenderer {

    private String backgroundColor = "\u001B[48;5;22m";
    private String backgroundResetColor = "\u001B[0m";
    private String herbivoreSprite = "\uD83D\uDC07";
    private String predatorSprite = "\uD83D\uDC05";
    private String obstacleSprite = "\uD83E\uDEA8";
    private String plantSprite = "\uD83C\uDF31";
    private final int gameMapWidth;
    private final int gameMapHeight;

    public MapConsoleRenderer(String backgroundColor, String backgroundResetColor, String herbivoreSprite, String predatorSprite, String obstacleSprite, String plantSprite, int gameMapWidth, int gameMapHeight) {
        this.backgroundColor = backgroundColor;
        this.backgroundResetColor = backgroundResetColor;
        this.herbivoreSprite = herbivoreSprite;
        this.predatorSprite = predatorSprite;
        this.obstacleSprite = obstacleSprite;
        this.plantSprite = plantSprite;
        this.gameMapWidth = gameMapWidth;
        this.gameMapHeight = gameMapHeight;
    }

    public MapConsoleRenderer(int gameMapWidth, int gameMapHeight) {
        this.gameMapWidth = gameMapWidth;
        this.gameMapHeight = gameMapHeight;
    }

    public void render(GameMap gameMap) {

        System.out.println("+" + "-----+".repeat(gameMapWidth));

        for (int horizontal = 0; horizontal <= gameMapHeight; horizontal++) {
            StringBuilder line = new StringBuilder("|");
            for (int vertical = 0; vertical <= gameMapWidth; vertical++) {
                Coordinates coordinates = new Coordinates(horizontal, vertical);
                if (gameMap.isFieldEmpty(coordinates)) {
                    line.append(getSpriteForEmptyField());
                }
                else {
                    line.append(getEntitySprite(gameMap.getEntity(coordinates)));
                }

                line.append("|");
            }

            line.append(backgroundResetColor);
            System.out.println(line);
            System.out.println("+" + "-----+".repeat(gameMapWidth));
        }

    }

    private String getSpriteForEmptyField() {
        return (backgroundColor + "    ");
    }

    private String selectEmojiForEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()){
            case "Herbivore" -> herbivoreSprite;
            case "Predator" -> predatorSprite;
            case "Obstacle" -> obstacleSprite;
            case "Plant" -> plantSprite;
            default -> throw new IllegalArgumentException("Unsupported entity type: " + entity.getClass().getSimpleName());
        };
    }
    private String getEntitySprite(Entity entity) {
        return (backgroundColor+ " " + selectEmojiForEntity(entity) + " ");
    }
}
