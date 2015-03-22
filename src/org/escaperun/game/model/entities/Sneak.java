package org.escaperun.game.model.entities;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.entities.skills.SneakSkillsContainer;
import org.escaperun.game.model.entities.skills.sneak.Arrow;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class Sneak extends Avatar {

    private SneakSkillsContainer sneakSkills;

    public Sneak(Position initialPosition) {

        super(new Decal('@', Color.BLACK, Color.ORANGE), initialPosition);
        sneakSkills = new SneakSkillsContainer(this);
    }

    @Override
    public void attack(Entity defender, Skill skill) {
        // TODO: Add the SuccessSkillGenerator once it's done.
    }

    @Override
    public void visit(TwoHandedWeapon thw) {
        Logger.getInstance().pushMessage("You cannot equip the "+thw.getName()+"! You cannot equip two-handed weapons!");
    }

    @Override
    public void visit(OneHandedWeapon ohw) {
        Logger.getInstance().pushMessage("You cannot equip the "+ohw.getName()+"! You cannot equip one-handed weapons!");
    }

    @Override
    public void visit(FistWeapon fw) {
        Logger.getInstance().pushMessage("You cannot equip the "+fw.getName()+"! You cannot equip fist weapons!");
    }

    @Override
    public void visit(BoomstickWeapon bsw) {
        equipWeaponItem(bsw);
    }

    @Override
    public void visit(BowWeapon bw) {
        equipWeaponItem(bw);
    }

    @Override
    public void visit(ThrowingKnivesWeapon tkw) {
        equipWeaponItem(tkw);
    }

    @Override
    public void visit(StaffWeapon sw) {
        Logger.getInstance().pushMessage("You cannot equip the "+sw.getName()+"! You cannot equip fist !");
    }

    @Override
    public Projectile skill1() {
        //TODO: this probably needs to work with SneakSkillsContainer or something
        return new Arrow(0,0,0,this,20,this.getDirection(),this.getCurrentPosition(), 3);
    }

    @Override
    public SneakSkillsContainer getSkillsContainer(){

        return sneakSkills;
    }
}
