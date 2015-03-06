package org.escaperun.game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardListener implements KeyListener {
    private static final int CHAR_LIMIT = 65536;    // There are 2^16 = 65536 possible chars.
    private final boolean[] pressed = new boolean[CHAR_LIMIT];
    private String typed = "";

    @Override
    public void keyTyped(KeyEvent e) {
        typed += e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed[Character.toLowerCase(e.getKeyChar())] = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed[Character.toLowerCase(e.getKeyChar())] = true;
    }

    public boolean getKey(char c){
        return pressed[c];
    }

    public String getTyped() {
        String temp = typed;
        typed = "";
        return temp;
    }

}