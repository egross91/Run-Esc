package org.escaperun.game.controller.keyboard;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.EnumMap;
import java.util.Map;

public class KeyBindings implements Saveable {

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
        binding.put(ke, keycode);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element ele = dom.createElement("KeyBindings");
        parent.appendChild(ele);

        for (Map.Entry<KeyType, Integer> entry : binding.entrySet()) {
            ele.setAttribute(entry.getKey().toString(), entry.getValue().toString());
        }
        return ele;
    }

    @Override
    public KeyBindings load(Element thenode) {
        Element node = (Element) thenode.getElementsByTagName("KeyBindings").item(0);

        KeyBindings bind = new KeyBindings();
        for (KeyType type : KeyType.values()) {
            if (node.hasAttribute(type.toString())) {
                int value = Integer.parseInt(node.getAttribute(type.toString()));
                bind.setBinding(type, value);
            }
        }
        return bind;
    }
}
