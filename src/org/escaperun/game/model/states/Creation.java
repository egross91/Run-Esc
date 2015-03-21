package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Smasher;
import org.escaperun.game.model.entities.Sneak;
import org.escaperun.game.model.entities.Summoner;
import org.escaperun.game.model.entities.handlers.MovementHandler;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;

public class Creation extends GameState {

    private OptionContainer options;

    public Creation() {
        options = new OptionContainer(new Option[][] {
                {new SelectableOption("SMASHER"){
                    public GameState getNextState() {
                        Stage stage = setupStage(new Smasher(new Position(0, 0)));

                        return new Playing(stage);
                    }
                },
                new SelectableOption("SUMMONER") {
                    Stage stage = setupStage(new Summoner(new Position(0, 0)));

                    public GameState getNextState() {
                        return new Playing(stage);
                    }
                },
                new SelectableOption("SNEAK") {
                    Stage stage = setupStage(new Sneak(new Position(0, 0)));

                    public GameState getNextState() {
                        return new Playing(stage);
                    }
                }},
                {new SelectableOption("RETURN"){
                    public GameState getNextState() {
                        return new Main();
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

    private Stage setupStage(Avatar avatar) {
        Stage stage = new Stage();
        avatar.setMovementHandler(new MovementHandler(stage, avatar));
        stage.setAvatar(avatar);

        return stage;
    }
}
