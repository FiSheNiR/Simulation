package org.example.Service;

import org.example.Entity.Entity;
import org.example.Entity.Herbivore;
import org.example.Entity.Plant;
import org.example.Map.CoordinateShift;
import org.example.Map.Coordinates;
import org.example.Map.GameMap;

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
                    .filter(c -> map.getEntityByCoordinates(c) instanceof Plant)
                    .findFirst().orElse(null);
        } else {
            return getAvailableMoveFields(coordinates).stream()
                    .filter(c -> map.getEntityByCoordinates(c) instanceof Herbivore)
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
        for (CoordinateShift shift : fieldsToCheck()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);
                result.add(newCoordinates);
            }
        }
        return result;
    }

    private List<Coordinates> emptyFieldsNear(Coordinates coordinates) {
        return getAvailableMoveFields(coordinates).stream().filter(map::isFieldEmpty).toList();
    }

    private Set<CoordinateShift> fieldsToCheck() {

        Set<CoordinateShift> result = new HashSet<>();

        for (int fileShift = -1; fileShift <= 1; fileShift++) {
            for (int verticalShift = -1; verticalShift <= 1; verticalShift++) {
                if ((fileShift == 0) && (verticalShift == 0)) {
                    continue;
                }
                result.add(new CoordinateShift(fileShift, verticalShift));
            }
        }

        return result;
    }
}
