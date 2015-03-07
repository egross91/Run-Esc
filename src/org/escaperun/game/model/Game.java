package org.escaperun.game.model;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.model.states.exit.Exit;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public class Game implements Renderable {

    private KeyBindings keybindings;
    private GameState state;

    public Game() {
        this.keybindings = new KeyBindings();
        //This.gamestate
    }

    public void update(boolean[] pressed) {
        if (state == null) return;
        state.update(keybindings, pressed);
    }

    public boolean isOver() {
        return state == null || state instanceof Exit;
    }

    public Decal[][] getRenderable() {
        if (state == null) return null;
        return state.getRenderable();
    }
}