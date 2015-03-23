package org.escaperun.game.model.states;

import org.escaperun.game.controller.Sound;
import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Smasher;
import org.escaperun.game.model.entities.Sneak;
import org.escaperun.game.model.entities.Summoner;
import org.escaperun.game.model.entities.handlers.MovementHandler;
import org.escaperun.game.model.entities.npc.adversarial.MeleeNPC;
import org.escaperun.game.model.entities.npc.ai.MeleeAI;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.serialization.SaveManager;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class Creation extends GameState {

    private OptionContainer options;

    public Creation() {
        options = new OptionContainer(new Option[][] {
                {new SelectableOption("SMASHER"){
                    public GameState getNextState() {
                        Sound.INTRO_MUSIC.stop();
                        Sound.PLAYING.play();

                        Stage stage = setupStage(new Smasher(new Position(0, 0)));
                        stage.getAvatar().visit(new OneHandedWeapon(new Decal('t', Color.BLACK, Color.BLUE), "The Annihilator", "A weapon of mass destruction fo' yo' momma."));

                        return new Playing(stage);
                    }
                },
                new SelectableOption("SUMMONER") {
                    Stage stage = setupStage(new Summoner(new Position(0, 0)));

                    public GameState getNextState() {
                        Sound.INTRO_MUSIC.stop();
                        Sound.PLAYING.play();
                        return new Playing(stage);
                    }
                },
                new SelectableOption("SNEAK") {
                    Stage stage = setupStage(new Sneak(new Position(0, 0)));

                    public GameState getNextState() {
                        Sound.INTRO_MUSIC.stop();
                        Sound.PLAYING.play();
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
        try {
            Stage test = SaveManager.load(System.getProperty("user.dir") + "/profiles/teststage.xml", new Stage());
            stage = test;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        avatar.setMovementHandler(new MovementHandler(stage, avatar,8));
        stage.setAvatar(avatar);

        //DEBUG //TODO: REMOVE DEBUG CODE.
        MeleeNPC npc = new MeleeNPC(new Decal('*', Color.BLACK, Color.RED), new Position(30,30),5);
        npc.setMovementHandler(new MovementHandler(stage, npc, 8));
        MeleeAI ai = new MeleeAI(stage, npc);

        return stage;
    }
}
