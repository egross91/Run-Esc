package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.view.Decal;

public class Main extends GameState {

    private OptionContainer optionContainer;

    public Main() {
        Option[][] options = new Option[][]{
                {new SelectableOption("New Game") {
                    @Override
                    public GameState getNextState() {
                        return new Creation();
                    }
                }},
                {new SelectableOption("Load Game") {
                    @Override
                    public GameState getNextState() {
                        return null;
                    }
                }},
                {new SelectableOption("Exit") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                }}
        };
        optionContainer = new OptionContainer(options, OptionContainer.ContainerType.CENTERED);
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        return optionContainer.update(bindings, pressed);
    }

    @Override
    public Decal[][] getRenderable() {
        return optionContainer.getRenderable();
    }
}