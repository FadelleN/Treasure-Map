package org.treasure_map.object;

import org.treasure_map.utils.Coordinates;
import org.treasure_map.utils.InstructionType;
import org.treasure_map.utils.Orientation;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

    private final int length;

    private final int width;

    private final List<Mountains> mountains;

    private final List<Treasure> treasure;

    private Adventurer adventurer;

    public GameMap(int length, int width) {
        this.length = length;
        this.width = width;
        mountains = new ArrayList<>();
        treasure = new ArrayList<>();
        adventurer = null;
    }

    public static GameMap initializeMap(List<String> mapLines) {
        GameMap gameMap = null;

        if (mapLines != null && !mapLines.isEmpty()) {
            for (String line : mapLines) {
                List<String> mapInstructions = List.of(line.split(" - "));

                String instructionType = mapInstructions.getFirst();
                gameMap = updateGameMap(instructionType, mapInstructions, gameMap);
            }
        }

        return gameMap;
    }

    private static GameMap updateGameMap(String instructionType, List<String> mapInstructions, GameMap gameMap) {
        switch (InstructionType.fromChar(instructionType)) {
            case InstructionType.CARTE :
                int length = Integer.parseInt(mapInstructions.get(1));
                int width = Integer.parseInt(mapInstructions.get(2));

                if (length > 0 && width > 0) {
                    gameMap = new GameMap(length, width);
                }
                break;
            case InstructionType.MONTAGNE:

                Coordinates coordinatesMountain = new Coordinates(Integer.parseInt(mapInstructions.get(1)), Integer.parseInt(mapInstructions.get(2)));

                if (hasValidCoordinates(gameMap, coordinatesMountain)) {
                    gameMap.mountains.add(new Mountains(coordinatesMountain));
                }
                break;
            case InstructionType.TRESOR:
                Coordinates coordinatesTreasure = new Coordinates(Integer.parseInt(mapInstructions.get(1)), Integer.parseInt(mapInstructions.get(2)));
                int numberOfTreasures = Integer.parseInt(mapInstructions.get(3));

                if (hasValidCoordinates(gameMap, coordinatesTreasure) && numberOfTreasures > 0) {
                    gameMap.treasure.add(new Treasure(coordinatesTreasure, numberOfTreasures));
                }
                break;
            case InstructionType.AVENTURIER:
                String name = mapInstructions.get(1);
                Coordinates coordinatesAdventurer = new Coordinates(Integer.parseInt(mapInstructions.get(1)), Integer.parseInt(mapInstructions.get(2)));
                Orientation orientation = Orientation.valueOf(mapInstructions.get(3).toUpperCase());
                String movements = mapInstructions.get(4);

                if (hasValidCoordinates(gameMap, coordinatesAdventurer)) {
                    gameMap.adventurer = new Adventurer(name, coordinatesAdventurer, orientation, movements);
                }
                break;
            case null, default:
                System.out.println("Invalid line instruction: " + mapInstructions);
        }
        return gameMap;
    }

    private static boolean hasValidCoordinates(GameMap gameMap, Coordinates coordinates) {
        return gameMap != null && coordinates.getXAxis() >= 0 && coordinates.getXAxis() < gameMap.length && coordinates.getYAxis() >= 0 && coordinates.getYAxis() < gameMap.width;
    }

    public int getSize() {
        return width * length;
    }

    public int getItemsNumber() {
        return mountains.size() + treasure.size();
    }
}
