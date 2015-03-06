package org.escaperun.game.tests;

import org.escaperun.game.controller.KeyBindings;
import org.escaperun.game.controller.KeyEnum;
import org.escaperun.game.controller.Keyboard;
import org.escaperun.game.controller.KeyboardListener;
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
        KeyboardListener keyboardListener = new KeyboardListener();
        KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, 'w');

        //Test pressed
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertFalse(keyboard.isHeld(KeyEnum.UP));

        //Test Held
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertTrue(keyboard.isHeld(KeyEnum.UP));

        //Test Held
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertTrue(keyboard.isHeld(KeyEnum.UP));

        //Test release
        keyboardListener.keyReleased(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertFalse(keyboard.isHeld(KeyEnum.UP));
    }

    @Test
    public void testIsPressedOnce() throws Exception {
        Keyboard keyboard = new Keyboard();
        KeyboardListener keyboardListener = new KeyboardListener();
        KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, 'w');

        //Test pressed
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertTrue(keyboard.isPressedOnce(KeyEnum.UP));

        //Test Held
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertFalse(keyboard.isPressedOnce(KeyEnum.UP));

        //Test Held
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertFalse(keyboard.isPressedOnce(KeyEnum.UP));

        //Test release
        keyboardListener.keyReleased(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertFalse(keyboard.isPressedOnce(KeyEnum.UP));
    }

    @Test
    public void testIsPressed() throws Exception {
        Keyboard keyboard = new Keyboard();
        KeyboardListener keyboardListener = new KeyboardListener();
        KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, 'w');

        //Test pressed
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertTrue(keyboard.isPressed(KeyEnum.UP));

        //Test Held
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertTrue(keyboard.isPressed(KeyEnum.UP));

        //Test Held
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertTrue(keyboard.isPressed(KeyEnum.UP));

        //Test release
        keyboardListener.keyReleased(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertFalse(keyboard.isPressed(KeyEnum.UP));
    }

    @Test
    public void testSetKeyBindings() throws Exception {

    }

    @Test
    public void testSetKey() throws Exception {
        Keyboard keyboard = new Keyboard();
        KeyboardListener keyboardListener = new KeyboardListener();
        KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, 's');

        //Test down key
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertTrue(keyboard.isPressed(KeyEnum.DOWN));

        //Change key associated with 's'
        keyboard.setKey(KeyEnum.UP, 's');
        keyboardListener.keyPressed(keyEvent);
        keyboard.poll(keyboardListener);
        Assert.assertFalse(keyboard.isPressed(KeyEnum.DOWN));
        Assert.assertTrue(keyboard.isPressed(KeyEnum.UP));
    }

    @Test
    public void testGetTyped() throws Exception {
        Keyboard keyboard = new Keyboard();
        KeyboardListener keyboardListener = new KeyboardListener();
        String message = "Hello \tWORLD";

        //Type hello world
        for (int i = 0; i < message.length(); i++){
            KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, message.charAt(i));
            keyboardListener.keyTyped(keyEvent);
        }
        keyboard.poll(keyboardListener);
        Assert.assertEquals(message, keyboard.getTyped());

        //Type good bye
        message = "Farewell cruel WOrlD";
        for (int i = 0; i < message.length(); i++){
            KeyEvent keyEvent = new KeyEvent(new Component() {}, 0,0,0,0, message.charAt(i));
            keyboardListener.keyTyped(keyEvent);
        }
        keyboard.poll(keyboardListener);
        Assert.assertEquals(message, keyboard.getTyped());
    }
}