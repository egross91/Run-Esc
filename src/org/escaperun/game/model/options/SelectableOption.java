package org.escaperun.game.model.options;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.view.Decal;

import java.awt.*;

public abstract class SelectableOption extends Option {

    public final String text;
    public final Decal[][] unselected;
    public final Decal[][] selected;

    public SelectableOption(String text) {
        this.text = text;
        unselected = new Decal[1][text.length()];
        for (int i = 0; i < text.length(); i++) {
            unselected[0][i] = new Decal(text.charAt(i), Color.BLACK, Color.WHITE);
        }
        selected = new Decal[1][text.length()];
        for (int i = 0; i < text.length(); i++) {
            selected[0][i] = new Decal(text.charAt(i), Color.LIGHT_GRAY.darker(), Color.BLUE);
        }
    }

    public abstract GameState getNextState();

    public Decal[][] getRenderable(boolean focused) {
        if (focused) return this.selected;
        else return unselected;
    }

    public GameState update(KeyBindings bind, boolean[] pressed) {
        boolean action = pressed[bind.getBinding(KeyType.ACTION)];

        if (action) {
            pressed[bind.getBinding(KeyType.ACTION)] = false;
            return getNextState();
        }
        return null;
    }
}
