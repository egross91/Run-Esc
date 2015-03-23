package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.serialization.SaveManager;
import org.escaperun.game.view.Decal;

public class Pause extends GameState {

    private final Playing previous;
    private OptionContainer optionContainer;

    public Pause(Playing previous) {
        if (previous == null)
            throw new RuntimeException("previous should not be null");
        this.previous = previous;
        final Playing previ = previous;
        final Stage test = previous.stage;
        Option[][] options = new Option[][]{
                {new SelectableOption("Save Game") {
                    @Override
                    public GameState getNextState() {
                        return new Saving(previ);
                    }
                }},
                {new SelectableOption("Return") {
                    @Override
                    public GameState getNextState() {
                        return Pause.this.previous;
                    }
                }},
                {new SelectableOption("Exit to Main Menu") {
                    @Override
                    public GameState getNextState() {
                        return new Main();
                    }
                }}
        };
        optionContainer = new OptionContainer(options, OptionContainer.ContainerType.CENTERED);
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        boolean escape = pressed[bindings.getBinding(KeyType.EXIT)];
        if (escape) {
            pressed[bindings.getBinding(KeyType.EXIT)] = false;
            return previous;
        }
        return optionContainer.update(bindings, pressed);
    }

    @Override
    public Decal[][] getRenderable() {
        return optionContainer.getRenderable();
    }
}
