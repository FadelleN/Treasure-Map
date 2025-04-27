package org.treasure_map.object;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameMapTest {


    static Stream<Arguments> provideValidListAndInt() {
        return Stream.of(
                Arguments.of(List.of("C - 3 - 4"), 12),
                Arguments.of(List.of("C - 6 - 11"), 66),
                Arguments.of(List.of("C - 2 - 1"), 2)
        );
    }

    static Stream<Arguments> provideInvalidListAndInt() {
        return Stream.of(
                Arguments.of(List.of("3 - 4")),
                Arguments.of(List.of("")),
                Arguments.of(List.of("C - 2 - -1"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidListAndInt")
    void testInitializeMapWithValidParameters(List<String> mapLines, int size) {

        GameMap gameMap = GameMap.initializeMap(mapLines);


        assertThat(gameMap).isNotNull();
        assertThat(gameMap.getSize()).isEqualTo(size);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidListAndInt")
    void testInitializeMapWithInvalidParameters(List<String> mapLines) {

        GameMap gameMap = GameMap.initializeMap(mapLines);

        assertThat(gameMap).isNull();
    }
}