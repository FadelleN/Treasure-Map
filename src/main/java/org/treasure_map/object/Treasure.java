package org.treasure_map.object;

import org.treasure_map.utils.Coordinates;

public class Treasure {

    private final Coordinates coordinates;
    private int numberOfTreasures;

    public Treasure(Coordinates coordinates, int numberOfTreasures) {
        this.coordinates = coordinates;
        this.numberOfTreasures = numberOfTreasures;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }

    public void decreaseNumberOfTreasures() {
        numberOfTreasures--;
    }
}
