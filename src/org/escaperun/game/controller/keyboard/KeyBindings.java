package org.escaperun.game.controller.keyboard;

import java.util.EnumMap;
import java.util.Map;

public class KeyBindings {

    private EnumMap<KeyType, Integer> binding = new EnumMap<KeyType, Integer>(KeyType.class);

    {
        for (KeyType en : KeyType.values()) {
            setBinding(en, en.defaultKeycode);
        }
    }

    public int getBinding(KeyType ke) {
        return binding.get(ke);
    }

    public void setBinding(KeyType ke, int keycode) {
        for (Map.Entry<KeyType, Integer> bind : binding.entrySet()) {
            if (bind.getKey() != ke && keycode == bind.getValue()) {
                throw new IllegalArgumentException("keycode " + keycode + " already bound");
            }
        }
        binding.put(ke, keycode);
    }
}
