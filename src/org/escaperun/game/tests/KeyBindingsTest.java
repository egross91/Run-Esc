package org.escaperun.game.tests;

import org.escaperun.game.controller.KeyBindings;
import org.escaperun.game.controller.KeyEnum;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyBindingsTest {

    @Test
    public void testSetAndGetPrimary() throws Exception {
        KeyBindings kb = new KeyBindings();
        Assert.assertEquals(null, kb.getPrimary(KeyEnum.UP));
        kb.setPrimary(KeyEnum.UP, 'w');
        Assert.assertEquals('w', kb.getPrimary(KeyEnum.UP).charValue());
    }
}