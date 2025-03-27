package org.example;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        map.setupDefaultEntitiesPositions();

        MapConsoleRenderer renderer = new MapConsoleRenderer();
        renderer.render(map);
    }
}