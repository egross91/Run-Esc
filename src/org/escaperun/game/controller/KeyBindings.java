package org.escaperun.game.controller;

import java.security.Key;
import java.util.ArrayList;
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
        ArrayList<Character> list = new ArrayList<Character>();

        //Check for null reference.
        for(KeyEnum ke : KeyEnum.values()){
            try {
                list.add(primary.get(ke));
            }
            catch (NullPointerException e){
                primary.put(ke, null);
            }
        }

        //Check key collusions
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                for (int j = i+1; j < list.size(); j++) {
                    if (list.get(i).equals(list.get(j)))
                        throw new IllegalArgumentException("Keys can not map to the same character.");
                }
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
            if (setChar == c) { //Should only ever be one character mapped
                primary.put(keyEnum, null);
                break;
            }
        }
        primary.put(ke, c);

    }
}
