package org.escaperun.game.model;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.model.states.Exit;
import org.escaperun.game.model.states.Main;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public class Game implements Renderable {
    private GameState state;
    private KeyBindings keybindings;

    public Game() {
        this.keybindings = new KeyBindings();
        this.state = new Main();
    }

    public Game(KeyBindings loaded) {
        this.keybindings = loaded;
        this.state = new Main();
    }

    public KeyBindings getKeyBindings() {
        return keybindings;
    }

    public void update(boolean[] pressed) {
        if (state == null) return;
        GameState ret = state.update(keybindings, pressed);
        if (ret != null) state = ret;
    }

    public boolean isOver() {
        return state == null || state instanceof Exit;
    }

    public Decal[][] getRenderable() {
        if (state == null) return null;
        return state.getRenderable();
    }
}