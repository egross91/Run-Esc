package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;

public class Playing extends GameState {

    private Stage stage;

    public Playing(Stage stage) {
        this.stage = stage;
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        handleMovement();
        stage.tick();
        return null;
    }

    private void handleMovement() {
        // TODO
    }

    @Override
    public Decal[][] getRenderable() {
        return stage.getRenderable();
    }
}
