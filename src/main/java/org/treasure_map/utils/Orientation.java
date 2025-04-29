package org.treasure_map.utils;

import java.util.Objects;

public enum Orientation {
    NORD("N"),
    SUD("S"),
    EST("E"),
    OUEST("O");

    private final String orientationCharacter;

    Orientation(String orientationCharacter) {
        this.orientationCharacter = orientationCharacter;
    }

    public static Orientation fromChar(String character) {
        for (Orientation orientation : Orientation.values()) {
            if (Objects.equals(orientation.orientationCharacter, character)) {
                return orientation;
            }
        }
        return null;
    }

    public String getOrientationCharacter() {
        return orientationCharacter;
    }
}
