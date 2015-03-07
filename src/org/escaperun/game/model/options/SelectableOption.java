package org.escaperun.game.model.options;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.states.GameState;

public abstract class SelectableOption extends Option {

    public SelectableOption(String text) {
        super(text);
    }

    public abstract GameState getNextState();

    public GameState update(KeyBindings bind, boolean[] pressed) {
        boolean action = pressed[bind.getBinding(KeyType.ACTION)];

        if (action) {
            pressed[bind.getBinding(KeyType.ACTION)] = false;
            return getNextState();
        }
        return null;
    }
}
