package org.treasure_map.object;

import org.treasure_map.utils.Coordinates;
import org.treasure_map.utils.InstructionType;
import org.treasure_map.utils.Movement;
import org.treasure_map.utils.Orientation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameMap {

    private final int length;

    private final int width;

    private final List<Mountain> mountains;

    private final List<Treasure> treasures;

    public int turnNumberLeft;

    private Adventurer adventurer;

    public GameMap(int length, int width) {
        this.length = length;
        this.width = width;
        mountains = new ArrayList<>();
        treasures = new ArrayList<>();
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

                if (isNotOutOfBound(gameMap, coordinatesMountain)) {
                    gameMap.mountains.add(new Mountain(coordinatesMountain));
                }
                break;
            case InstructionType.TRESOR:
                Coordinates coordinatesTreasure = new Coordinates(Integer.parseInt(mapInstructions.get(1)), Integer.parseInt(mapInstructions.get(2)));
                int numberOfTreasures = Integer.parseInt(mapInstructions.get(3));

                if (isNotOutOfBound(gameMap, coordinatesTreasure) && numberOfTreasures > 0) {
                    gameMap.treasures.add(new Treasure(coordinatesTreasure, numberOfTreasures));
                }
                break;
            case InstructionType.AVENTURIER:
                String name = mapInstructions.get(1);
                Coordinates coordinatesAdventurer = new Coordinates(Integer.parseInt(mapInstructions.get(2)), Integer.parseInt(mapInstructions.get(3)));
                Orientation orientation = Orientation.fromChar(mapInstructions.get(4));
                List<Movement> movements = Stream.of(mapInstructions.get(5).split("")).map(Movement::fromChar).collect(Collectors.toList());

                if (isNotOutOfBound(gameMap, coordinatesAdventurer)) {
                    gameMap.adventurer = new Adventurer(name, coordinatesAdventurer, orientation, movements);
                }
                break;
            case COMMENTAIRE:
                break;
            case null, default:
                System.out.println("Invalid line instruction: " + mapInstructions);
        }
        return gameMap;
    }

    private static boolean isNotOutOfBound(GameMap gameMap, Coordinates coordinates) {
        return gameMap != null && coordinates.getXAxis() >= 0 && coordinates.getXAxis() < gameMap.length && coordinates.getYAxis() >= 0 && coordinates.getYAxis() < gameMap.width;
    }

    public void passTurn() {
        if (adventurer != null) {
            List<Movement> adventurerMovements = adventurer.getMovements();
            Movement movement = adventurerMovements.getFirst();

            switch (movement) {
                case AVANCER:
                    Coordinates forwardCoordinates = adventurer.goForward();
                    if (forwardCoordinates != null
                            && isNotOutOfBound(this, forwardCoordinates)
                            && mountains.stream().map(Mountain::getCoordinates).noneMatch(mountainCoordinates -> mountainCoordinates.getXAxis() == forwardCoordinates.getXAxis() && mountainCoordinates.getYAxis() == forwardCoordinates.getYAxis())) {
                        adventurer.setCoordinates(forwardCoordinates);
                        Optional<Treasure> treasureOptional = treasures.stream().filter(treasure -> treasure.getCoordinates().getXAxis() == forwardCoordinates.getXAxis() && treasure.getCoordinates().getYAxis() == forwardCoordinates.getYAxis()).findFirst();

                        if (treasureOptional.isPresent()) {
                            Treasure foundTreasure = treasureOptional.get();
                            foundTreasure.decreaseNumberOfTreasures();
                            adventurer.pickUpTreasure();
                            if (foundTreasure.getNumberOfTreasures() == 0) {
                                treasures.remove(foundTreasure);
                            }
                        }
                    }
                    break;
                case GAUCHE:
                    adventurer.turnLeft();
                    break;
                case DROITE:
                    adventurer.turnRight();
                    break;
            }
            adventurerMovements.removeFirst();
            turnNumberLeft = adventurerMovements.size();
        }
        else {
            System.out.println("No adventurer to move");
        }
    }

    public int getSize() {
        return width * length;
    }

    public int getItemsNumber() {
        return mountains.size() + treasures.size();
    }

    public String getMapInStringFormat() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("C - ").append(length).append(" - ").append(width).append("\n");
        for (Mountain mountain : mountains) {
            stringBuilder.append("M - ").append(mountain.getCoordinates().getXAxis()).append(" - ").append(mountain.getCoordinates().getYAxis()).append("\n");
        }
        for (Treasure treasure : treasures) {
            stringBuilder.append("T - ").append(treasure.getCoordinates().getXAxis()).append(" - ").append(treasure.getCoordinates().getYAxis()).append(" - ").append(treasure.getNumberOfTreasures()).append("\n");
        }
        stringBuilder.append("A - ").append(adventurer.getName()).append(" - ").append(adventurer.getCoordinates().getXAxis()).append(" - ").append(adventurer.getCoordinates().getYAxis()).append(" - ").append(adventurer.getOrientation().getOrientationCharacter()).append(" - ").append(adventurer.getPickedUpTreasure()).append("\n");

        return stringBuilder.toString();
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }
}
