package org.treasure_map.object;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

    private final int length;

    private final int width;

    private final List<Mountains> mountains;

    private final List<Treasure> treasure;

    public GameMap(int length, int width) {
        this.length = length;
        this.width = width;
        mountains = new ArrayList<>();
        treasure = new ArrayList<>();
    }

    public static GameMap initializeMap(List<String> mapLine) {
        GameMap gameMap = null;

        for (String line : mapLine) {
            List<String> splittedString = List.of(line.split(" - "));

            String Object = splittedString.get(0);
            switch (Object) {
                case "C":
                    int length = Integer.parseInt(splittedString.get(1));
                    int width = Integer.parseInt(splittedString.get(2));

                    if (length > 0 && width > 0) {
                        gameMap = new GameMap(length, width);
                    }
                    break;
                case "M":
                    int xAxisMountains = Integer.parseInt(splittedString.get(1));
                    int yAxisMountains = Integer.parseInt(splittedString.get(2));

                    if (xAxisMountains > 0 && yAxisMountains > 0 && gameMap != null) {
                        gameMap.mountains.add(new Mountains(xAxisMountains, yAxisMountains));
                    }
                    break;
                case "T":
                    int xAxisTreasure = Integer.parseInt(splittedString.get(1));
                    int yAxisTreasure = Integer.parseInt(splittedString.get(2));
                    int numberOfTreasures = Integer.parseInt(splittedString.get(3));

                    if (xAxisTreasure > 0 && yAxisTreasure > 0 && gameMap != null) {
                        gameMap.treasure.add(new Treasure(xAxisTreasure, yAxisTreasure, numberOfTreasures));
                    }
                    break;
                default:
                    return null;
            }
        }
        return gameMap;
    }

    public int getSize() {
        return width * length;
    }

    public int getItemsNumber() {
        return mountains.size() + treasure.size();
    }
}
