package org.escaperun.game.controller.keyboard;

import java.awt.event.KeyEvent;

public enum KeyType {

    //Menu
    SHOP(KeyEvent.VK_P),
    INVENTORY(KeyEvent.VK_I),
    LEVEL_UP(KeyEvent.VK_L),
    ACTION(KeyEvent.VK_ENTER),
    BACKSPACE(KeyEvent.VK_BACK_SPACE),
    EXIT(KeyEvent.VK_ESCAPE),

    //Movement
    UP(KeyEvent.VK_W),
    DOWN(KeyEvent.VK_S),
    LEFT(KeyEvent.VK_A),
    RIGHT(KeyEvent.VK_D),
    UPLEFT(KeyEvent.VK_Q),
    UPRIGHT(KeyEvent.VK_E),
    DOWNLEFT(KeyEvent.VK_Z),
    DOWNRIGHT(KeyEvent.VK_C),

    //Hotkeys
    HOTKEY1(KeyEvent.VK_X),
    HOTKEY2(KeyEvent.VK_B),
    HOTKEY3(KeyEvent.VK_N),
    HOTKEY4(KeyEvent.VK_M),
    ATTACK(KeyEvent.VK_SHIFT),
    INTERACT(KeyEvent.VK_SPACE);

    KeyType(int defaultKeycode) {
        this.defaultKeycode = defaultKeycode;
    }

    public final int defaultKeycode;

    public String getKey() {
        switch (this.defaultKeycode) {
            case KeyEvent.VK_I:
                return "I";
            case KeyEvent.VK_L:
                return "L";
            case KeyEvent.VK_ENTER:
                return "Enter";
            case KeyEvent.VK_BACK_SPACE:
                return "Backspace";
            case KeyEvent.VK_ESCAPE:
                return "X";
            case KeyEvent.VK_W:
                return "W";
            case KeyEvent.VK_S:
                return "S";
            case KeyEvent.VK_D:
                return "D";
            case KeyEvent.VK_A:
                return "A";
            case KeyEvent.VK_Q:
                return "Q";
            case KeyEvent.VK_E:
                return "E";
            case KeyEvent.VK_Z:
                return "Z";
            case KeyEvent.VK_C:
                return "C";
            case KeyEvent.VK_X:
                return "X";
            case KeyEvent.VK_B:
                return "B";
            case KeyEvent.VK_N:
                return "N";
            case KeyEvent.VK_SHIFT:
                return "Shift";
            case KeyEvent.VK_SPACE:
                return "Space";
            default:
                throw new IllegalArgumentException("Pressed the wrong Key :P");
        }
    }
}
