package org.escaperun.game.controller;

import java.awt.event.KeyListener;
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
        //TODO: Load default keybindings
        keyBindings = new KeyBindings();
        setDefaultKeyBindings(keyBindings);
        keyboardListener = new KeyboardListener();
        keyStateMap = new EnumMap<KeyEnum, KeyState>(KeyEnum.class);
    }

    public Keyboard(KeyBindings kb) {
        keyboardListener = new KeyboardListener();
        keyStateMap = new EnumMap<KeyEnum, KeyState>(KeyEnum.class);
        keyBindings = kb;
    }

    /** Update the state of the keys. */
    public void poll() {
        for (KeyEnum ke : KeyEnum.values()) {
            char c = keyBindings.getPrimary(ke);
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
/*
    public KeyState getKeyState(KeyEnum ke) {
        return keyStateMap.get(ke);
    }
*/
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

    public void setKey(KeyEnum ke, char c) {
        keyBindings.setPrimary(ke, c);
    }

    public void setKeyboardListener(KeyboardListener kl) {
        keyboardListener = kl;
    }

    public KeyboardListener getKeyboardListener() {
        return keyboardListener;
    }

    private static void setDefaultKeyBindings(KeyBindings kb) {
        kb.setPrimary(KeyEnum.UP, 'w');
        kb.setPrimary(KeyEnum.DOWN, 's');
        kb.setPrimary(KeyEnum.LEFT, 'a');
        kb.setPrimary(KeyEnum.RIGHT, 'd');
    }
}


