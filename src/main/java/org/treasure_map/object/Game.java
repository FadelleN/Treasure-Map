package org.treasure_map.object;

import org.treasure_map.Main;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    public void initializeGame() {
        System.out.println("Hello and welcome to the treasure map game !\nChoose the file you want to play with");

        File selectedFile = selectFileWithDialog();
        if (selectedFile != null) {
            List<String> linesFile = getLinesFromFile(selectedFile);
            GameMap gameMap = GameMap.initializeMap(linesFile);

            if (gameMap != null) {
                playGame(gameMap);
                writeFile(gameMap.getMapInStringFormat());
            } else {
                System.out.println("Your file has invalid inputs");
            }
        } else {
            System.out.println("No file selected or invalid extension.");
        }
    }

    public File selectFileWithDialog() {
        JFileChooser fileChooser = new JFileChooser();
        return fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile() != null && getFileExtension(fileChooser.getSelectedFile()).equals("txt")
                ? fileChooser.getSelectedFile()
                : null;
    }

    public List<String> getLinesFromFile(File file) {
        List<String> lines = new ArrayList<>();
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error on reading file: " + e.getMessage());
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error on reading file: ", e);
            }
        }
        return lines;
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return name.substring(lastDotIndex + 1);
        }
        return "";
    }

    private void playGame(GameMap gameMap) {
        do {
            gameMap.passTurn();
            System.out.println(gameMap.turnNumberLeft + " remaining turn");
        } while (gameMap.turnNumberLeft != 0);
        Adventurer adventurer = gameMap.getAdventurer();
        System.out.println(adventurer.getPickedUpTreasure() > 0
                ? "Congrats, " + adventurer.getName() + " found " + adventurer.getPickedUpTreasure() + " treasures !"
                : "Sorry, " + adventurer.getName() + " didn't find any treasure !");
    }

    public void writeFile(String content) {
        try {
            Files.writeString(Path.of("C:\\Users\\Orion\\IdeaProjects\\Treasure-Map\\src\\main\\resources\\resultFile.txt"), content, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.err.println("Error while writing result : " + e.getMessage());
        }
    }

}