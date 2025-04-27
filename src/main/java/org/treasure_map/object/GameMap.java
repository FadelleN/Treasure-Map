package org.treasure_map.object;

import java.util.List;

public class GameMap {

    private final int length;

    private final int width;

    public GameMap(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public static GameMap initializeMap(List<String> mapLine) {
        for (String line : mapLine) {
            List<String> splittedString = List.of(line.split(" - "));

            if (splittedString.get(0).equals("C")) {
                int length = Integer.parseInt(splittedString.get(1));
                int width = Integer.parseInt(splittedString.get(2));

                if (length <= 0 || width <= 0) {
                    return null;
                } else {
                    return new GameMap(length, width);
                }
            }
        }
        return null;
    }

    public int getSize() {
        return width * length;
    }
}
