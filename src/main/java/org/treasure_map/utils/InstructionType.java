package org.treasure_map.utils;

import java.util.Objects;

public enum InstructionType {

    CARTE("C"),
    MONTAGNE("M"),
    TRESOR("T"),
    AVENTURIER("A"),
    COMMENTAIRE("#");

    private final String typeChar;

    InstructionType(String typeChar) {
        this.typeChar = typeChar;
    }

    public static InstructionType fromChar(String character) {
        for (InstructionType instructionType : InstructionType.values()) {
            if (Objects.equals(instructionType.typeChar, character)) {
                return instructionType;
            }
        }
        return null;
    }
}
