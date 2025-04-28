package org.treasure_map.object;

public class Treasure {

    private final int xAxis;
    private final int yAxis;
    private final int numberOfTreasures;

    public Treasure(int xAxis, int yAxis, int numberOfTreasures) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.numberOfTreasures = numberOfTreasures;
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }
}
