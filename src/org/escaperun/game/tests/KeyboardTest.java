package org.escaperun.game.tests;

import org.escaperun.game.controller.KeyBindings;
import org.escaperun.game.controller.KeyEnum;
import org.escaperun.game.controller.Keyboard;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardTest {

    @Test
    public void testIsHeld() throws Exception {
        Keyboard keyboard = new Keyboard();
        KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, 'w');

        //Test pressed
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertFalse(keyboard.isHeld(KeyEnum.UP));

        //Test Held
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertTrue(keyboard.isHeld(KeyEnum.UP));

        //Test Held
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertTrue(keyboard.isHeld(KeyEnum.UP));

        //Test release
        keyboard.getKeyboardListener().keyReleased(keyEvent);
        keyboard.poll();
        Assert.assertFalse(keyboard.isHeld(KeyEnum.UP));
    }

    @Test
    public void testIsPressedOnce() throws Exception {
        Keyboard keyboard = new Keyboard();
        KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, 'w');

        //Test pressed
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertTrue(keyboard.isPressedOnce(KeyEnum.UP));

        //Test Held
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertFalse(keyboard.isPressedOnce(KeyEnum.UP));

        //Test Held
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertFalse(keyboard.isPressedOnce(KeyEnum.UP));

        //Test release
        keyboard.getKeyboardListener().keyReleased(keyEvent);
        keyboard.poll();
        Assert.assertFalse(keyboard.isPressedOnce(KeyEnum.UP));

    }

    @Test
    public void testIsPressed() throws Exception {
        Keyboard keyboard = new Keyboard();
        KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, 'w');

        //Test pressed
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertTrue(keyboard.isPressed(KeyEnum.UP));

        //Test Held
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertTrue(keyboard.isPressed(KeyEnum.UP));

        //Test Held
        keyboard.getKeyboardListener().keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertTrue(keyboard.isPressed(KeyEnum.UP));

        //Test release
        keyboard.getKeyboardListener().keyReleased(keyEvent);
        keyboard.poll();
        Assert.assertFalse(keyboard.isPressed(KeyEnum.UP));
    }

    @Test
    public void testSetKeyBindings() throws Exception {

    }

    @Test
    public void testSetKey() throws Exception {
        Keyboard keyboard = new Keyboard();
        KeyListener keyListener = keyboard.getKeyboardListener();
        KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, 's');

        //Test down key
        keyListener.keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertTrue(keyboard.isPressed(KeyEnum.DOWN));

        //Change key associated with 's'
        keyboard.setKey(KeyEnum.UP, 's');
        keyListener.keyPressed(keyEvent);
        keyboard.poll();
        Assert.assertFalse(keyboard.isPressed(KeyEnum.DOWN));
        Assert.assertTrue(keyboard.isPressed(KeyEnum.UP));
    }
}