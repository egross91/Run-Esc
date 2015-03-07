package org.escaperun.game.model;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.model.states.exit.Exit;
import org.escaperun.game.model.states.mainmenu.MainMenu;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public class Game implements Renderable {

    private KeyBindings keybindings;
    private GameState state;

    public Game() {
        this.keybindings = new KeyBindings();
        this.state = new MainMenu();
    }

    public Game(KeyBindings loaded) {
        this.keybindings = loaded;
        this.state = new MainMenu();
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