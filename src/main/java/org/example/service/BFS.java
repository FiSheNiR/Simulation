package org.example.service;

import org.example.entity.Entity;
import org.example.entity.Herbivore;
import org.example.entity.Plant;
import org.example.map.Coordinates;
import org.example.map.GameMap;

import java.util.*;

public class BFS {

    private final GameMap map;
    private final Random random = new Random();

    public BFS(GameMap map) {
        this.map = map;
    }

    public Coordinates isTargetNear(Coordinates coordinates, Class<? extends Entity> className) {
        if (className == Herbivore.class) {
            return getAvailableMoveFields(coordinates).stream()
                    .filter(c -> map.getEntity(c) instanceof Plant)
                    .findFirst().orElse(null);
        } else {
            return getAvailableMoveFields(coordinates).stream()
                    .filter(c -> map.getEntity(c) instanceof Herbivore)
                    .findFirst().orElse(null);
        }
    }

    public Deque<Coordinates> findPathToTarget(Coordinates start, Class<? extends Entity> targetClass) {
        Deque<Coordinates> toVisit = new ArrayDeque<>();
        toVisit.add(start);

        Set<Coordinates> visited = new HashSet<>();
        visited.add(start);

        Map<Coordinates, Coordinates> parentMap = new HashMap<>();
        Coordinates target = null;

        while (!toVisit.isEmpty()) {
            Coordinates current = toVisit.pollFirst();

            if (isTargetNear(current, targetClass) != null) {
                target = current;
                break;
            }

            for (Coordinates neighbor : emptyFieldsNear(current)) {
                if (!visited.contains(neighbor)) {
                    toVisit.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        if (target != null) {
            return reconstructPath(parentMap, start, target);
        }

        return new LinkedList<>();
    }

    private Deque<Coordinates> reconstructPath(Map<Coordinates, Coordinates> parentMap, Coordinates start, Coordinates goal) {
        Deque<Coordinates> path = new LinkedList<>();
        Coordinates current = goal;

        while (!current.equals(start)) {
            path.addFirst(current);
            current = parentMap.get(current);
        }

        return path;
    }

    public Coordinates getRandomShiftCoordinates(Coordinates coordinates) {
        if (emptyFieldsNear(coordinates).isEmpty()) {
            return coordinates;
        }
        int randomIndex = random.nextInt(emptyFieldsNear(coordinates).size());
        return emptyFieldsNear(coordinates).get(randomIndex);
    }


    private List<Coordinates> getAvailableMoveFields(Coordinates coordinates) {
        List<Coordinates> result = new ArrayList<>();
        for (Coordinates shift : shiftCoordinates()) {
            if (canShift(coordinates, shift)) {
                Coordinates newCoordinates = new Coordinates(coordinates.row + shift.row, coordinates.column + shift.column);
                result.add(newCoordinates);
            }
        }
        return result;
    }

    private List<Coordinates> emptyFieldsNear(Coordinates coordinates) {
        return getAvailableMoveFields(coordinates).stream().filter(map::isFieldEmpty).toList();
    }

    private List<Coordinates> shiftCoordinates() {

        List<Coordinates> result = new ArrayList<>();

        result.add(new Coordinates(0, 1));
        result.add(new Coordinates(0, -1));
        result.add(new Coordinates(1, 1));
        result.add(new Coordinates(1, -1));
        result.add(new Coordinates(1, 0));
        result.add(new Coordinates(-1, -1));
        result.add(new Coordinates(-1, 1));
        result.add(new Coordinates(-1, 0));

        return result;
    }

    private boolean canShift(Coordinates coordinate, Coordinates shift) {
        int h = coordinate.row + shift.row;
        int v = coordinate.column + shift.column;

        return  h >= 0 &&
                v >= 0 &&
                v <= map.getGameMapWidth() &&
                h <= map.getGameMapHeight();
    }
}
