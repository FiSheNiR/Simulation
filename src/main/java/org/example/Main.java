package org.example;

import org.example.simulation.Settings;
import org.example.simulation.Simulation;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(Settings.SIMULATION_TURNS, Settings.TIME_SLEEP_BETWEEN_TURNS, Settings.GAME_MAP_WIDTH, Settings.GAME_MAP_HEIGHT);
        simulation.startSimulation();


    }
}