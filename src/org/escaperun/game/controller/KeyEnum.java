package org.escaperun.game.controller;

public enum KeyEnum {
    //Menu
    INVENTORY("Inventory"),
    ENTER("Enter"),
    ESC("Esc"),

    //Movement
    UP("N"),
    DOWN("S"),
    LEFT("W"),
    RIGHT("E"),
    UPLEFT("NW"),
    UPRIGHT("NE"),
    DOWNLEFT("SW"),
    DOWNRIGHT("SE");

    private String name;

    private KeyEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
