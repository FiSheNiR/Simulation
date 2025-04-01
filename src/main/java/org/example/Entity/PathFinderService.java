package org.example.Entity;
import org.example.Map.Coordinates;

import java.util.Deque;
import java.util.List;

public interface PathFinderService {

    Coordinates isGrassNear(Coordinates coordinates);
    Coordinates isHerbivoreNear(Coordinates coordinates);

    Deque<Coordinates> findPathToGrass(Coordinates coordinates);

    Deque<Coordinates> findPathToHerbivore(Coordinates coordinates);
}
