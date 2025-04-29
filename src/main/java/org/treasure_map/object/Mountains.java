package org.treasure_map.object;

import org.treasure_map.utils.Coordinates;

public class Mountains {

    private final Coordinates coordinates;

    public Mountains(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
