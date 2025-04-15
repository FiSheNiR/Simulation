package org.example.simulation;

import org.example.actions.*;
import org.example.entity.*;
import org.example.map.GameMap;
import org.example.map.MapConsoleRenderer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    private final GameMap gameMap;
    public int moveCounter = 0;

    private static volatile boolean isPaused = false;
    private final static String PAUSE = "p";
    private final static String RESUME = "r";
    private final int turns;
    private final int timeSleepBetweenTurns;
    private final MapConsoleRenderer mapConsoleRenderer;

    List<Action> initActions = new ArrayList<>();
    List<Action> turnActions = new ArrayList<>();

    public Simulation(int turns, int timeSleepBetweenTurns, int gameMapWidth, int gameMapHeight) {
        this.turns = turns;
        this.timeSleepBetweenTurns = timeSleepBetweenTurns;
        mapConsoleRenderer = new MapConsoleRenderer(gameMapWidth, gameMapHeight) ;
        gameMap = new GameMap(gameMapWidth, gameMapHeight);
    }

    public void startSimulation() {
        pauseSimulation();
        createActions();
        for (Action action : initActions) {
            action.execute(gameMap);
        }
        while (moveCounter < turns) {
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
            Thread.sleep(timeSleepBetweenTurns);
            mapConsoleRenderer.render(gameMap);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void pauseSimulation() {
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.printf("Press '%s' to pause or '%s' to resume:", PAUSE, RESUME);
                System.out.println();
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase(PAUSE)) {
                    isPaused = true;
                    System.out.println("Simulation paused.");
                } else if (input.equalsIgnoreCase(RESUME)) {
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
        turnActions.add(new SpawnEntityAction(Herbivore.class));
        turnActions.add(new SpawnEntityAction(Plant.class));
    }

}
