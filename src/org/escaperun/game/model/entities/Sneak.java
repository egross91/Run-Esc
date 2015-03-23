package org.escaperun.game.model.entities;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.controller.Sound;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.entities.skills.SneakSkillsContainer;
import org.escaperun.game.model.entities.skills.smasher.Cleave;
import org.escaperun.game.model.entities.skills.sneak.Arrow;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Sneak extends Avatar {

    private SneakSkillsContainer sneakSkills;

    public Sneak(Position initialPosition) {

        super(new Decal('@', Color.BLACK, Color.ORANGE), initialPosition);
        sneakSkills = new SneakSkillsContainer(this);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element avatar = dom.createElement("Sneak");
        super.save(dom, avatar);
        sneakSkills.save(dom, avatar);
        parent.appendChild(avatar);
        return avatar;
    }

    @Override
    public Sneak load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("Sneak") != null && node.getElementsByTagName("Sneak").getLength() > 0)
            us = (Element) node.getElementsByTagName("Sneak").item(0);

        Sneak sneak = new Sneak(new Position(0,0));

        Element entityPart = (Element) us.getElementsByTagName("Entity").item(0);
        sneak.helperLoad(entityPart);

        Element skills = (Element) us.getElementsByTagName("SneakSkillsContainer").item(0);
        sneak.sneakSkills = new SneakSkillsContainer(null).load(skills, sneak);
        return sneak;
    }

    private void helperLoad(Element node) {
        super.load(node);
    }

    @Override
    public void playAttackSound() {
        Sound.CASTSPELL.play();
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
        equipItem(bsw);
    }

    @Override
    public void visit(BowWeapon bw) {
        equipItem(bw);
    }

    @Override
    public void visit(ThrowingKnivesWeapon tkw) {
        equipItem(tkw);
    }

    @Override
    public void visit(StaffWeapon sw) {
        Logger.getInstance().pushMessage("You cannot equip the "+sw.getName()+"! You cannot equip fist !");
    }
    public void tick() {
        super.tick();
        attackTimer.tick();
    }
    private Timer attackTimer = new Timer(0);
    @Override
    public ActiveSkill attemptSkillCast1(Logger log) {
        return skill1();
    }

    private Projectile skill1() {
        //TODO: this probably needs to work with SneakSkillsContainer or something

        EquipableItem weap = this.getEquipment().getItemAtSlot(EquipableItem.EquipmentSlot.WEAPON.ordinal());
        if (weap == null) {
            Logger.getInstance().pushMessage("You have nothing to attack with!");
            return null;
        } else if (weap instanceof FistWeapon) {
            if (attackTimer.getTicksSince() >= 22) {
                attackTimer.reset();
                Cleave clv = new Cleave(0, 0, 0, this, 2, getDirection(), getCurrentPosition(), 15);
                clv.setDecal(new Decal('#', Color.BLACK, Color.ORANGE.darker()));
                return clv;
            }
        } else if (weap instanceof BowWeapon) {
            if (attackTimer.getTicksSince() >= 14) {
                attackTimer.reset();
                return new Arrow(15, 0, 0, this, 20, this.getDirection(), this.getCurrentPosition(), 3);
            }
        } else if (weap instanceof ThrowingKnivesWeapon) {
            if (attackTimer.getTicksSince() >= 8) {
                attackTimer.reset();
                Arrow ar = new Arrow(15, 0, 0, this, 20, this.getDirection(), this.getCurrentPosition(), 3);
                ar.setDecal(new Decal('-', Color.BLACK, Color.GRAY.brighter()));
                return ar;
            }
        }
        return null;
    }

    public ActiveSkill attemptSkillCast2(Logger log) {
        return null;
    }
    public ActiveSkill attemptSkillCast3(Logger log) {
        return null;
    }

    @Override
    public SneakSkillsContainer getSkillsContainer(){

        return sneakSkills;
    }
}
