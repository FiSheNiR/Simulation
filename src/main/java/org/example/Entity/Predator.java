package org.example.Entity;

import org.example.Coordinates;

public class Predator extends Creature{

    public int attackRate;

    public Predator(Coordinates coordinates, Integer speed, Integer health, int attackRate) {
        super(coordinates, speed, health);
        this.attackRate = attackRate;
    }

    @Override
    public void makeMove() {

    }

    public void travel(){}

    public void attack(){}
}
