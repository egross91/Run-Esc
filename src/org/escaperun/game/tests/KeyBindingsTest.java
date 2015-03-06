package org.escaperun.game.tests;

import org.escaperun.game.controller.KeyBindings;
import org.escaperun.game.controller.KeyEnum;
import org.junit.Assert;
import org.junit.Test;

import java.util.EnumMap;

import static org.junit.Assert.*;

public class KeyBindingsTest {

    @Test
    public void testSetAndGetPrimary() throws Exception {
        KeyBindings kb = new KeyBindings();
        Assert.assertEquals(null, kb.getPrimary(KeyEnum.UP));
        kb.setPrimary(KeyEnum.UP, 'w');
        Assert.assertEquals('w', kb.getPrimary(KeyEnum.UP).charValue());
    }

    @Test

    public void testConstructors() throws Exception {
        KeyBindings kb = new KeyBindings();
        Assert.assertEquals(null, kb.getPrimary(KeyEnum.UP));
        kb.setPrimary(KeyEnum.UP, 'w');
        Assert.assertEquals('w', kb.getPrimary(KeyEnum.UP).charValue());

        //Test enum of null references
        EnumMap<KeyEnum, Character> map = new EnumMap<KeyEnum, Character>(KeyEnum.class);
        map.put(KeyEnum.UP, 'w');
        kb = new KeyBindings(map);
        Assert.assertEquals(null, kb.getPrimary(KeyEnum.DOWN));

        //Test collusions
        map = new EnumMap<KeyEnum, Character>(KeyEnum.class);
        map.put(KeyEnum.UP, 'w');
        map.put(KeyEnum.DOWN, 'w');
        try {
            kb = new KeyBindings(map);
            throw new RuntimeException("IllegalArgumentException not thrown.");
        }
        catch (IllegalArgumentException e){
            Assert.assertEquals("Keys can not map to the same character.", e.getMessage());
        }
    }
}