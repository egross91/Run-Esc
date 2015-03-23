package org.escaperun.game.controller.keyboard;

import java.awt.event.KeyEvent;
import java.security.Key;

public enum KeyType {

    //Menu
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
    //adding these extra hotkeys for the additional skills that are needed
    HOTKEY2(KeyEvent.VK_B),
    HOTKEY3(KeyEvent.VK_N),
    HOTKEY4(KeyEvent.VK_M),
    //
    INTERACT(KeyEvent.VK_SPACE);

    KeyType(int defaultKeycode) {
        this.defaultKeycode = defaultKeycode;
    }

    public final int defaultKeycode;
}
