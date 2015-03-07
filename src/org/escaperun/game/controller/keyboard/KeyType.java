package org.escaperun.game.controller.keyboard;

import java.awt.event.KeyEvent;

public enum KeyType {

    //Menu
    INVENTORY(KeyEvent.VK_I),
    ACTION(KeyEvent.VK_ENTER),
    EXIT(KeyEvent.VK_ESCAPE),

    //Movement
    UP(KeyEvent.VK_W),
    DOWN(KeyEvent.VK_S),
    LEFT(KeyEvent.VK_A),
    RIGHT(KeyEvent.VK_R),
    UPLEFT(KeyEvent.VK_Q),
    UPRIGHT(KeyEvent.VK_E),
    DOWNLEFT(KeyEvent.VK_Z),
    DOWNRIGHT(KeyEvent.VK_C);

    KeyType(int defaultKeycode) {
        this.defaultKeycode = defaultKeycode;
    }

    public final int defaultKeycode;
}
