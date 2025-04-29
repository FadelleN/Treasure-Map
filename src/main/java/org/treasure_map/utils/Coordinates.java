package org.treasure_map.utils;

public class Coordinates {

    private final int xAxis;
    private final int yAxis;

    public Coordinates(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }
}
