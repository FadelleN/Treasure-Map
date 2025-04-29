package org.treasure_map.object;

import org.treasure_map.utils.Coordinates;

public class Mountain {

    private final Coordinates coordinates;

    public Mountain(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
