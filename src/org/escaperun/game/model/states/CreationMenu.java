package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Smasher;
import org.escaperun.game.model.entities.Sneak;
import org.escaperun.game.model.entities.Summoner;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;

public class CreationMenu extends GameState {

    private OptionContainer options;

    public CreationMenu() {
        options = new OptionContainer(new Option[][] {
                {new SelectableOption("SMASHER"){
                    public GameState getNextState() {
                        return new Playing(new Stage(new Smasher(new Position(0, 0))));
                    }
                },
                new SelectableOption("SUMMONER") {
                    public GameState getNextState() {
                        return new Playing(new Stage(new Summoner(new Position(0, 0))));
                    }
                },
                new SelectableOption("SNEAK") {
                    public GameState getNextState() {
                        return new Playing(new Stage(new Sneak(new Position(0, 0))));
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
