package org.escaperun.game.controller;

import java.util.EnumMap;

//NOTE: This is intended to be passed among the GameStates for finding keys.
//      However, the poll() is to be called in the update loop of Game.

public class Keyboard {
    private KeyBindings keyBindings;
    private KeyboardListener keyboardListener;      //TODO: Decide whether or not to remove this and have poll() take keyboardListener as input or leave as be.
    private EnumMap<KeyEnum, KeyState> keyStateMap;

    private enum KeyState {
        RELEASED,
        PRESSED,
        HELD
    }

    public Keyboard() {
        keyBindings = new KeyBindings();
        keyboardListener = new KeyboardListener();
        keyStateMap = new EnumMap<KeyEnum, KeyState>(KeyEnum.class);

        setDefaultKeyBindings(keyBindings);
        resetKeyStateMap(keyStateMap);
    }

    public Keyboard(KeyBindings kb) {
        keyBindings = kb;
        keyboardListener = new KeyboardListener();
        keyStateMap = new EnumMap<KeyEnum, KeyState>(KeyEnum.class);

        resetKeyStateMap(keyStateMap);
    }

    /** Update the state of the keys. */
    public void poll() {
        for (KeyEnum ke : KeyEnum.values()) {
            Character c = keyBindings.getPrimary(ke);
            if (c == null) continue;
            boolean isPressed = keyboardListener.getKey(c);
            if (isPressed) {
                KeyState ks = keyStateMap.get(ke);
                if (ks.equals(KeyState.RELEASED))
                    keyStateMap.put(ke, KeyState.PRESSED);
                else if (ks.equals(KeyState.PRESSED))
                    keyStateMap.put(ke, KeyState.HELD);
            }
            else
                keyStateMap.put(ke, KeyState.RELEASED);
        }
    }

    public boolean isHeld(KeyEnum ke){
        if(keyStateMap.get(ke).equals(KeyState.HELD))
            return true;
        return false;
    }

    public boolean isPressedOnce(KeyEnum ke){
        if(keyStateMap.get(ke).equals(KeyState.PRESSED))
            return true;
        return false;
    }

    public boolean isPressed(KeyEnum ke){
        KeyState ks = keyStateMap.get(ke);
        if(ks.equals(KeyState.PRESSED) || ks.equals(KeyState.HELD))
            return true;
        return false;
    }

    public void setKeyBindings(KeyBindings kb ) {
        keyBindings = kb;
    }

    public void setKey(KeyEnum ke, Character c) {
        //NOTE: Currently the responsibility of not changing static keys will be outside of this class.
        keyBindings.setPrimary(ke, c);
        resetKeyStateMap(keyStateMap);
    }

    public void setKeyboardListener(KeyboardListener kl) {
        keyboardListener = kl;
    }

    public KeyboardListener getKeyboardListener() {
        return keyboardListener;
    }

    private static void setDefaultKeyBindings(KeyBindings kb) {
        //TODO: Load default keybindings
        //Movement
        kb.setPrimary(KeyEnum.UP, 'w');
        kb.setPrimary(KeyEnum.DOWN, 's');
        kb.setPrimary(KeyEnum.LEFT, 'a');
        kb.setPrimary(KeyEnum.RIGHT, 'd');

        //In Game
        kb.setPrimary(KeyEnum.INVENTORY, 'i');
    }

    private static void resetKeyStateMap(EnumMap<KeyEnum, KeyState> em){
        for(KeyEnum ke : KeyEnum.values()){
            em.put(ke, KeyState.RELEASED);
        }
    }
}


