package org.escaperun.game.model.options;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.view.Decal;
import java.awt.*;

public abstract class Option {

    protected boolean permanentFocus;

    public boolean isPermanentFocus() {
        return permanentFocus;
    }

    public abstract Decal[][] getRenderable(boolean focused);
    public abstract GameState update(KeyBindings bind, boolean[] pressed);
}