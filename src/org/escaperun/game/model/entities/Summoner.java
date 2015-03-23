package org.escaperun.game.model.entities;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.*;
import org.escaperun.game.model.entities.skills.summoner.Bane;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.weapons.MagicalWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class Summoner extends Avatar {
    private SummonerSkillsContainer summonerSkills;

    public Summoner(Position initialPosition) {
        super(new Decal('@', Color.BLACK, Color.CYAN), initialPosition);
        summonerSkills = new SummonerSkillsContainer(this);
    }


    @Override
    public void attack(Entity defender, Skill skill) {
        // TODO: Add SkillGeneratorSuccess and any other logic.
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
        Logger.getInstance().pushMessage("You cannot equip the "+bsw.getName()+"! You cannot equip boomstick weapons!");
    }

    @Override
    public void visit(BowWeapon bw) {
        Logger.getInstance().pushMessage("You cannot equip the "+bw.getName()+"! You cannot equip bow weapons!");
    }

    @Override
    public void visit(ThrowingKnivesWeapon tkw) {
        Logger.getInstance().pushMessage("You cannot equip the "+tkw.getName()+"! You cannot equip throwing-knife weapons!");
    }

    @Override
    public void visit(StaffWeapon sw) {
        equipItem(sw);
    }

    //change to possibly ActiveSkill
    @Override
    public Projectile skill1(){
        return new Bane(16 ,0,0,this,10,this.getDirection(),this.getCurrentPosition(), 5);
    }

    /*public ActiveSkill skill2(){

    }

    public ActiveSkill skill3() {

    }*/

    @Override
    public SummonerSkillsContainer getSkillsContainer(){
        return summonerSkills;
    }
}
