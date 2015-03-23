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
import org.escaperun.game.model.entities.npc.adversarial.RangedNPC;
import org.escaperun.game.model.entities.npc.ai.MeleeAI;
import org.escaperun.game.model.entities.npc.ai.RangedAI;
import org.escaperun.game.model.items.equipment.armors.ChestItem;
import org.escaperun.game.model.items.equipment.armors.RobeBottom;
import org.escaperun.game.model.items.equipment.armors.RobinHat;
import org.escaperun.game.model.items.equipment.armors.Shield;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.serialization.SaveManager;
import org.escaperun.game.view.Decal;

import java.awt.*;
import java.awt.font.NumericShaper;

public class Creation extends GameState {

    private OptionContainer options;

    public Creation() {
        options = new OptionContainer(new Option[][] {
                {new SelectableOption("SMASHER"){
                    public GameState getNextState() {
                        Sound.PLAYING1.play();

                        Stage stage = setupStage(new Smasher(new Position(1, 1)));
                        stage.getAvatar().getInventory().add(
                                new TwoHandedWeapon(new Decal('O', Color.BLACK, Color.BLUE), "OBBY MAUL", "GET WRECKED SON"));
                        stage.getAvatar().getInventory().add(
                                new OneHandedWeapon(new Decal('t', Color.BLACK, Color.BLUE), "The Dave", "A one-handed weapon."));
                        stage.getAvatar().getInventory().add(
                                new TwoHandedWeapon(new Decal('T', Color.BLACK, Color.BLUE), "The Aleksey", "A two-handed weapon."));
                        stage.getAvatar().getInventory().add(new ChestItem(new Decal('C', Color.BLACK, Color.GREEN)));
                        stage.getAvatar().getInventory().add(new Shield(new Decal('@', Color.BLACK, Color.GRAY)));
                        return new Playing(stage);
                    }
                },
                new SelectableOption("SUMMONER") {
                    Stage stage = setupStage(new Summoner(new Position(1, 1)));

                    public GameState getNextState() {
                        stage.getAvatar().getInventory().add(
                                new StaffWeapon(new Decal('S', Color.BLACK, Color.BLUE), "Some Dumb Staff", "A staff."));
                        stage.getAvatar().getInventory().add(new RobeBottom(new Decal('V', Color.BLACK, Color.BLUE.brighter())));
                        Sound.PLAYING1.play();
                        return new Playing(stage);
                    }
                },
                new SelectableOption("SNEAK") {
                    Stage stage = setupStage(new Sneak(new Position(1, 1)));

                    public GameState getNextState() {
                        Sound.PLAYING1.play();
                        stage.getAvatar().getInventory().add(
                                new FistWeapon(new Decal('B', Color.BLACK, Color.BLUE), "BRASS KNUCKLES", "GET WRECKED SON"));
                        stage.getAvatar().getInventory().add(
                                new BowWeapon(new Decal('>', Color.BLACK, Color.GREEN), "A BOW BRO", "Too cool."));
                        stage.getAvatar().getInventory().add(
                                new ThrowingKnivesWeapon(new Decal('|', Color.BLACK, Color.GRAY), "THROW THIS KNIFE", "C'mon, throw me."));
                        stage.getAvatar().getInventory().add(new RobinHat(new Decal('^', Color.BLACK, Color.GREEN)));
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
        try {
            Stage load = SaveManager.load(System.getProperty("user.dir") + "/assets/defaultmap.xml", new Stage());
            avatar.setMovementHandler(new MovementHandler(load, avatar, 8));
            load.setAvatar(avatar);
            return load;
        } catch (Exception ex) {}
        return null;
    }
}
