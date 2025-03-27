package org.example.Entity;

import org.example.Coordinates;

public class Predator extends Creature{

    private final int attackRate;

    public Predator(Coordinates coordinates) {
        super(coordinates, 2, 20);
        this.attackRate = 5;
    }

    @Override
    public void makeMove() {

    }

    public void travel(){}

    public void attack(){}
}
