package org.treasure_map.object;

import org.treasure_map.utils.Coordinates;
import org.treasure_map.utils.Movement;
import org.treasure_map.utils.Orientation;

import java.util.List;

public class Adventurer {

    private final String name;
    private Coordinates coordinates;
    private Orientation orientation;
    private final List<Movement> movements;
    private int pickedUpTreasure;

    public Adventurer(String name, Coordinates coordinates, Orientation orientation, List<Movement> movements) {
        this.name = name;
        this.coordinates = coordinates;
        this.orientation = orientation;
        this.movements = movements;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public Coordinates goForward() {
        return switch (orientation) {
            case NORD -> new Coordinates(coordinates.getXAxis(), coordinates.getYAxis() - 1);
            case SUD -> new Coordinates(coordinates.getXAxis(), coordinates.getYAxis() + 1);
            case EST -> new Coordinates(coordinates.getXAxis() + 1, coordinates.getYAxis());
            case OUEST -> new Coordinates(coordinates.getXAxis() - 1, coordinates.getYAxis());
            case null -> coordinates;
        };
    }

    public void turnLeft() {
        switch (orientation) {
            case NORD -> this.orientation = Orientation.OUEST;
            case SUD -> this.orientation = Orientation.EST;
            case EST -> this.orientation = Orientation.NORD;
            case OUEST -> this.orientation = Orientation.SUD;
        }
    }

    public void turnRight() {
        switch (orientation) {
            case NORD -> this.orientation = Orientation.EST;
            case SUD -> this.orientation = Orientation.OUEST;
            case EST -> this.orientation = Orientation.SUD;
            case OUEST -> this.orientation = Orientation.NORD;
        }
    }

    public void pickUpTreasure() {
        pickedUpTreasure++;
    }

    public int getPickedUpTreasure() {
        return pickedUpTreasure;
    }
}
