package org.treasure_map.object;

import org.treasure_map.Main;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            GameMap gameMap = initializeMap(linesFile);
        } else {
            System.out.println("No file selected.");
        }
    }

    private GameMap initializeMap(List<String> linesFile) {
        return null;
    }

    public File selectFileWithDialog() {
        JFileChooser fileChooser = new JFileChooser();
        return fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION
                ? fileChooser.getSelectedFile()
                : null;
    }

    public List<String> getLinesFromFile(File file) {
        if (file != null && getFileExtension(file).equals("txt")) {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error on reading file: " + e.getMessage());
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error on reading file: ", e);
            }
            return lines;
        }
        else {
            return new ArrayList<>();
        }
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return name.substring(lastDotIndex + 1);
        }
        return "";
    }

}
