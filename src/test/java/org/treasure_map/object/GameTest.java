package org.treasure_map.object;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

class GameTest {

    private final Game game = new Game();

    @BeforeEach
    void setUp() {
    }

    @Test
    void testInitializeGameWithValidFile() {
        File file = new File("C:\\Users\\Orion\\IdeaProjects\\Treasure-Map\\src\\main\\resources\\testFile.txt");

        List<String> linesFromFile = game.getLinesFromFile(file);

        assert(linesFromFile.size() == 4);
        assert(linesFromFile.getFirst().equals("first line"));
        assert(linesFromFile.get(1).isEmpty());
        assert(linesFromFile.get(2).equals("second line"));

    }

    @Test
    void testInitializeGameWithWrongExtensionFile() {

        File file = new File("C:\\Users\\Orion\\IdeaProjects\\Treasure-Map\\src\\main\\resources\\testFile.pdf");

        List<String> linesFromFile = game.getLinesFromFile(file);

        assert(linesFromFile.isEmpty());
    }

    @Test
    void testInitializeGameWithNoFile() {

        List<String> linesFromFile = game.getLinesFromFile(null);

        assert(linesFromFile.isEmpty());
    }
}