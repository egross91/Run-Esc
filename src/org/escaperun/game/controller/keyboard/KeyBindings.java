package org.escaperun.game.controller.keyboard;

import java.util.EnumMap;
import java.util.Map;

public class KeyBindings {

    private EnumMap<KeyEnum, Integer> binding = new EnumMap<KeyEnum, Integer>(KeyEnum.class);

    {
        for (KeyEnum en : KeyEnum.values()) {
            setBinding(en, en.defaultKeycode);
        }
    }

    public int getBinding(KeyEnum ke) {
        return binding.get(ke);
    }

    public void setBinding(KeyEnum ke, int keycode) {
        for (Map.Entry<KeyEnum, Integer> bind : binding.entrySet()) {
            if (bind.getKey() != ke && keycode == bind.getValue()) {
                throw new IllegalArgumentException("keycode " + keycode + " already bound");
            }
        }
        binding.put(ke, keycode);
    }
}
