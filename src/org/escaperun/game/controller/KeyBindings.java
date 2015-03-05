package org.escaperun.game.controller;

import java.security.Key;
import java.util.EnumMap;

public class KeyBindings {
    private EnumMap<KeyEnum, Character> primary;

    public KeyBindings() {
        primary = new EnumMap<KeyEnum, Character>(KeyEnum.class);
        for (KeyEnum ke : KeyEnum.values())
            primary.put(ke, null);
    }

    public KeyBindings(EnumMap<KeyEnum, Character> map){
        primary = map;
        for(KeyEnum ke : KeyEnum.values()){
            try {
                primary.get(ke);
            }
            catch (NullPointerException e){
                primary.put(ke, null);
            }
        }
    }

    public Character getPrimary(KeyEnum ke) {
        return primary.get(ke);
    }

    public void setPrimary(KeyEnum ke, Character c) {
        //Remove collusions of keys and characters.
        for (KeyEnum keyEnum : KeyEnum.values()){
            Character setChar = primary.get(keyEnum);
            if (setChar == c)
                primary.put(keyEnum, null);
        }
        primary.put(ke, c);

    }
}
