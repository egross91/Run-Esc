package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.view.Decal;

public class CreationMenu extends GameState {

    private OptionContainer options;

    public CreationMenu() {
        options = new OptionContainer(new Option[][] {
                {new SelectableOption("SMASHER"){
                    public GameState getNextState() {
                        return null;
                    }
                },
                new SelectableOption("SUMMONER") {
                    public GameState getNextState() {
                        return null;
                    }
                },
                new SelectableOption("SNEAK") {
                    public GameState getNextState() {
                        return null;
                    }
                }},
                {new SelectableOption("RETURN"){
                    public GameState getNextState() {
                        return new MainMenu();
                    }
                }}
        }, OptionContainer.ContainerType.CENTERED);
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        return options.update(bindings, pressed);
    }

    @Override
    public Decal[][] getRenderable() {
        return options.getRenderable();
    }
}
