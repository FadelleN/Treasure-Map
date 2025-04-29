package org.treasure_map.utils;

import java.util.Objects;

public enum Movement {
    AVANCER("A"),
    GAUCHE("G"),
    DROITE("D");

    private final String movementCharacter;

    Movement(String movementCharacter) {
        this.movementCharacter = movementCharacter;
    }

    public static Movement fromChar(String character) {
        for (Movement movement : Movement.values()) {
            if (Objects.equals(movement.movementCharacter, character)) {
                return movement;
            }
        }
        return null;
    }
}
