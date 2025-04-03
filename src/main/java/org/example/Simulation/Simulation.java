package org.example.Simulation;

import org.example.Actions.*;
import org.example.Entity.*;
import org.example.Map.GameMap;
import org.example.Map.MapConsoleRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    private final GameMap gameMap = new GameMap();
    public int moveCounter = 0;
    private final MapConsoleRenderer mapConsoleRenderer = new MapConsoleRenderer();
    private static volatile boolean isPaused = false;

    List<Action> initActions = new ArrayList<>();
    List<Action> turnActions = new ArrayList<>();

    public void startSimulation() {
        startInputThread();
        createActions();
        for (Action action : initActions) {
            action.execute(gameMap);
        }
        while (moveCounter < 100) {
            if (!isPaused) {
                nextTurn();
                moveCounter++;
                System.out.println("Ход номер: " + moveCounter);
            }else waitForResume();
        }

    }

    private void nextTurn(){
        try {
            for (Action action : turnActions) {
                action.execute(gameMap);
            }
            turnActions.removeLast();
            turnActions.removeLast();
            turnActions.add(new SpawnEntityAction(Herbivore.class, countEntities(Herbivore.class)));
            turnActions.add(new SpawnEntityAction(Plant.class, countEntities(Plant.class)));
            Thread.sleep(Settings.TIME_SLEEP_BETWEEN_TURNS);
            mapConsoleRenderer.render(gameMap);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void startInputThread() {
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Press 'p' to pause or 'r' to resume:");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("p")) {
                    isPaused = true;
                    System.out.println("Simulation paused.");
                } else if (input.equalsIgnoreCase("r")) {
                    isPaused = false;
                    System.out.println("Simulation resumed.");
                }
            }
        });
        inputThread.setDaemon(true);
        inputThread.start();
    }

    private void waitForResume() {
        try {
            synchronized (this) {
                while (isPaused) {
                    wait(500);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void createActions(){
        initActions.add(new SpawnEntityAction(Obstacle.class));
        initActions.add(new SpawnEntityAction(Predator.class));
        turnActions.add(new MoveEntityAction());
        turnActions.add(new SpawnEntityAction(Herbivore.class, countEntities(Herbivore.class)));
        turnActions.add(new SpawnEntityAction(Plant.class, countEntities(Plant.class)));
    }

    private int countEntities(Class<? extends Entity> className) {
        int count = (int) gameMap.getCurrentGameMap().values().stream()
                .filter(className::isInstance)
                .count();
        return Math.max(0, Settings.BASE_SPAWN_RATE - count);
    }
}
