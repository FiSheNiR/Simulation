package org.example.Map;

import org.example.Simulation.Settings;

public class Coordinates {

    public final Integer horizontalPosition;
    public final Integer verticalPosition;

    public Coordinates(Integer horizontalPosition, Integer verticalPosition) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    public Coordinates shift(CoordinateShift shift) {
        return new Coordinates(horizontalPosition + shift.horizontalShift,
                verticalPosition + shift.verticalShift);
    }

    public boolean canShift(CoordinateShift shift) {
        int h = horizontalPosition + shift.horizontalShift;
        int v = verticalPosition + shift.verticalShift;

        return  h >= Settings.HORIZONTAL_BOTTOM_BOUND &&
                v >= Settings.HORIZONTAL_BOTTOM_BOUND &&
                v <= Settings.AMOUNT_OF_VERTICAL_COLUMNS &&
                h <= Settings.AMOUNT_OF_HORIZONTAL_ROWS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;
        return horizontalPosition.equals(that.horizontalPosition) && verticalPosition.equals(that.verticalPosition);
    }

    @Override
    public int hashCode() {
        int result = horizontalPosition.hashCode();
        result = 31 * result + verticalPosition.hashCode();
        return result;
    }
}
