package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public class OptionContainer implements Renderable {

    public static enum ContainerType {
        CENTERED
    }

    private Option[][] options;
    private ContainerType type;
    private int selectedX = 0, selectedY = 0;

    public OptionContainer(Option[][] options, ContainerType type) {
        this.options = options;
        this.type = type;
    }

    public GameState update(KeyBindings bind, boolean[] pressed) {
        boolean up = pressed[bind.getBinding(KeyType.UP)];
        boolean down = pressed[bind.getBinding(KeyType.DOWN)];
        boolean left = pressed[bind.getBinding(KeyType.LEFT)];
        boolean right = pressed[bind.getBinding(KeyType.RIGHT)];

        return null;
    }

    @Override
    public Decal[][] getRenderable() {
        switch (type) {
            case CENTERED:
                return renderCenter(options);
            default:
                throw new IllegalArgumentException("containertype " + type.name() + " not supported");
        }
    }

    private static Decal[][] renderCenter(Option[][] options) {
        return null;
    }
}
