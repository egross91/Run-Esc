package org.escaperun.game.model.states.mainmenu;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.model.states.Option;
import org.escaperun.game.model.states.OptionContainer;
import org.escaperun.game.view.Decal;

public class MainMenu extends GameState {

    private OptionContainer optionContainer;

    public MainMenu() {
        Option[][] options = new Option[][]{
                {},
                {},
                {}
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
