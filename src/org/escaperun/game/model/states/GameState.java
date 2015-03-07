package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.view.Renderable;

public abstract class GameState implements Renderable {

    // a non-null return value indicates a
    // transition to returned game state
    public abstract GameState update(KeyBindings bindings, boolean[] pressed);
}
