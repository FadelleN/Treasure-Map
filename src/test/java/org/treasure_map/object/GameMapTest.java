package org.treasure_map.object;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameMapTest {


    static Stream<Arguments> provideValidParametersAndExpectedSize() {
        return Stream.of(
                Arguments.of(List.of("C - 3 - 4"), 12),
                Arguments.of(List.of("C - 6 - 11"), 66),
                Arguments.of(List.of("C - 2 - 1"), 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidParametersAndExpectedSize")
    void testInitializeMapWithValidParameters(List<String> mapLines, int size) {

        GameMap gameMap = GameMap.initializeMap(mapLines);


        assertThat(gameMap).isNotNull();
        assertThat(gameMap.getSize()).isEqualTo(size);
    }

    static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                Arguments.of(List.of("3 - 4")),
                Arguments.of(List.of("")),
                Arguments.of(List.of("C - 2 - -1")),
                Arguments.of(List.of("C - -2 - 1"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParameters")
    void testInitializeMapWithInvalidParameters(List<String> mapLines) {

        GameMap gameMap = GameMap.initializeMap(mapLines);

        assertThat(gameMap).isNull();
    }

    static Stream<Arguments> provideComplexValidParametersAndExpectedItemsNumber() {
        return Stream.of(
                Arguments.of(List.of("C - 3 - 4", "M - 1 - 1", "M - 2 - 2"), 2),
                Arguments.of(List.of("C - 5 - 5", "M - 3 - 4", "M - 4 - 1"), 2),
                Arguments.of(List.of("C - 2 - 1", "M - 0 - 0"), 1),
                Arguments.of(List.of("C - 10 - 10", "M - 4 - 4", "M - 6 - 7", "M - 2 - 5", "M - 2 - 6", "T - 3 - 4 - 2", "T - 1 - 1 - 2"), 6),
                Arguments.of(List.of("C - 3 - 3", "T - 2 - 2 - 1", "M - 2 - 1", "T - 1 - 1 - 3"), 3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideComplexValidParametersAndExpectedItemsNumber")
    void testInitializeMapWithComplexParameters(List<String> mapLines, int expectedItemsNumber) {

        GameMap gameMap = GameMap.initializeMap(mapLines);

        assertThat(gameMap).isNotNull();
        assertThat(gameMap.getItemsNumber()).isEqualTo(expectedItemsNumber);
    }

    static Stream<Arguments> provideComplexInvalidParametersAndExpectedItemsNumber() {
        return Stream.of(
                Arguments.of(List.of("C - 3 - 4", "M - -5 - 1", "M - 2 - 2"), 1),
                Arguments.of(List.of("C - 5 - 5", "M - 3 - 4", "M - 7 - 1"), 1),
                Arguments.of(List.of("C - 5 - 5", "M - 3 - 4", "T - 1 - 15 - 2"), 1),
                Arguments.of(List.of("M - 1 - 2", "C - 2 - 1"), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideComplexInvalidParametersAndExpectedItemsNumber")
    void testInitializeMapWithComplexInvalidParameters(List<String> mapLines, int expectedItemsNumber) {

        GameMap gameMap = GameMap.initializeMap(mapLines);

        assertThat(gameMap.getItemsNumber()).isEqualTo(expectedItemsNumber);
    }

    static Stream<Arguments> provideFullGameWithAdventurerAndExpectedMapInStringFormat() {
        return Stream.of(
                Arguments.of(List.of(
                        "C - 3 - 4", "M - 1 - 0", "M - 2 - 1", "T - 0 - 3 - 2", "T - 1 - 3 - 3", "A - Lara - 1 - 1 - S - AADADAGGA"),
                        "C - 3 - 4\nM - 1 - 0\nM - 2 - 1\nT - 1 - 3 - 2\nA - Lara - 0 - 3 - S - 3\n"),
                Arguments.of(List.of(
                        "C - 5 - 3", "M - 1 - 0", "M - 3 - 1", "T - 0 - 2 - 1", "T - 4 - 0 - 3", "A - Zoé - 2 - 2 - N - AAGADDAA"),
                        "C - 5 - 3\nM - 1 - 0\nM - 3 - 1\nT - 0 - 2 - 1\nT - 4 - 0 - 2\nA - Zoé - 4 - 0 - E - 1\n")
        );
    }

    @ParameterizedTest
    @MethodSource("provideFullGameWithAdventurerAndExpectedMapInStringFormat")
    void testResultOfMapInStringFormatWithAdventurer(List<String> mapLines, String expectedMapInStringFormat) {

        GameMap gameMap = GameMap.initializeMap(mapLines);

        do {
            gameMap.passTurn();
        } while (gameMap.turnNumberLeft != 0);

        assertThat(gameMap.getMapInStringFormat()).isEqualTo(expectedMapInStringFormat);
    }
}