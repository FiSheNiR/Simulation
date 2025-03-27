package org.example.Entity;

import org.example.Coordinates;

public class Herbivore extends Creature{

    public Herbivore(Coordinates coordinates, Integer speed, Integer health) {
        super(coordinates, speed, health);
    }

    @Override
    public void makeMove() {

    }

    public void travel(){}

    public void eat(){}
}
