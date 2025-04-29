package org.treasure_map.object;

import org.treasure_map.utils.Coordinates;
import org.treasure_map.utils.Orientation;

public class Adventurer {

    private final String name;
    private Coordinates coordinates;
    private Orientation orientation;
    private String movements;

    public Adventurer(String name, Coordinates coordinates, Orientation orientation, String movements) {
        this.name = name;
        this.coordinates = coordinates;
        this.orientation = orientation;
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

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
    }
}
