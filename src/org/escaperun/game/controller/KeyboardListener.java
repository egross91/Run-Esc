package org.escaperun.game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private final boolean[] pressed = new boolean[65536]; // There are 2^16 = 65536 possible chars.

    @Override
    public void keyTyped(KeyEvent e) {
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

}