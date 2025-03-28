package org.example.Entity;

import org.example.Map.Coordinates;

public class Herbivore extends Creature{

    public Herbivore(Coordinates coordinates) {
        super(coordinates, 1, 10);
    }

    @Override
    public void makeMove() {

    }

    public void travel(){}

    public void eat(){}
}
