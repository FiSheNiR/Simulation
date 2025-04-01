package org.example.Entity;

import org.example.Map.Coordinates;
import org.example.Map.GameMap;

import java.util.*;

public class BFS implements PathFinderService, Movable {

    private final GameMap map;

    public BFS(GameMap map) {
        this.map = map;
    }

    @Override
    public Coordinates isGrassNear(Coordinates coordinates) {
        return fieldsToCheck(coordinates).stream()
                .filter(c -> map.getEntityByCoordinates(c) instanceof Plant)
                .findFirst().orElse(null);
    }

    @Override
    public Coordinates isHerbivoreNear(Coordinates coordinates) {
        return fieldsToCheck(coordinates).stream()
                .filter(c -> map.getEntityByCoordinates(c) instanceof Herbivore)
                .findFirst().orElse(null);
    }

    @Override
    public Deque<Coordinates> findPathToGrass(Coordinates coordinates) {
       Deque<Coordinates> path = new ArrayDeque<>();
       Deque<Coordinates> toVisit = new ArrayDeque<>(fieldsToCheck(coordinates));
       while (!toVisit.isEmpty()) {
           Coordinates visiting = toVisit.pollFirst();
           path.add(visiting);

           if (isGrassNear(visiting) != null) break;
           toVisit.addAll(fieldsToCheck(visiting).stream().filter(c -> !path.contains(c)).toList());
       }

       return path;
    }

    @Override
    public Deque<Coordinates> findPathToHerbivore(Coordinates coordinates) {
        Deque<Coordinates> path = new ArrayDeque<>();
        Deque<Coordinates> toVisit = new ArrayDeque<>(fieldsToCheck(coordinates));
        while (!toVisit.isEmpty()) {
            Coordinates visiting = toVisit.pollFirst();
            path.add(visiting);

            if (isHerbivoreNear(visiting) != null) break;
            toVisit.addAll(fieldsToCheck(visiting).stream().filter(c -> !path.contains(c)).toList());
        }

        return path;
    }

    private List<Coordinates> fieldsToCheck(Coordinates coordinates) {
        return getAvailableMoveFields(map, coordinates);
    }
}
