package org.escaperun.game.controller;

import java.util.EnumMap;

public class KeyBindings {
    private EnumMap<KeyEnum, Character> primary;

    public KeyBindings() {
        primary = new EnumMap<KeyEnum, Character>(KeyEnum.class);
    }

    public char getPrimary(KeyEnum ke) {
        return primary.get(ke);
    }

    public void setPrimary(KeyEnum ke, char c) {
        primary.put(ke, c);
    }

    /*
    public char getSecondary(KeyEnum ke) {
        return map.get(ke).right;
    }

    public Pair<Character, Character> getPair(KeyEnum ke) {
        return map.get(ke);
    }
    */
}
