package org.treasure_map.object;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    void testInitializeGameWithValidFile() {
        Game game = new Game();

        File file = new File("C:\\Users\\Orion\\IdeaProjects\\Treasure-Map\\src\\main\\resources\\testFile.txt");

        List<String> linesFromFile = game.getLinesFromFile(file);

        assertThat(linesFromFile).isNotNull();
        assertThat(linesFromFile).isNotEmpty();
        assertThat(linesFromFile).hasSize(4);
        assertThat(linesFromFile.get(0)).isEqualTo("first line");
        assertThat(linesFromFile.get(1)).isEmpty();
        assertThat(linesFromFile.get(2)).isEqualTo("second line");

    }

//    @Test
    void testInitializeGameWithWrongExtensionFile() {
        Game game = new Game();

        File file = new File("C:\\Users\\Orion\\IdeaProjects\\Treasure-Map\\src\\main\\resources\\testFile.pdf");

        List<String> linesFromFile = game.getLinesFromFile(file);

        assertThat(linesFromFile).isEmpty();
    }

    @Test
    void testInitializeGameWithNoFile() {
        Game game = new Game();

        List<String> linesFromFile = game.getLinesFromFile(null);

        assertThat(linesFromFile).isEmpty();
    }
}