package org.escaperun.game.tests;

import org.escaperun.game.controller.KeyEnum;
import org.escaperun.game.controller.Keyboard;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardTest {

    @Test
    public void testPoll() throws Exception {

    }

    @Test
    public void testIsHeld() throws Exception {

    }

    @Test
    public void testIsPressedOnce() throws Exception {

    }

    @Test
    public void testIsPressed() throws Exception {
        Robot robot = new Robot();
        Keyboard keyboard = new Keyboard();
        robot.keyPress(KeyEvent.VK_W);  //Default UP is 'w';
        keyboard.poll();
        Assert.assertTrue(keyboard.isPressed(KeyEnum.DOWN));
    }

    @Test
    public void testSetKeyBindings() throws Exception {

    }

    @Test
    public void testSetKey() throws Exception {

    }
}