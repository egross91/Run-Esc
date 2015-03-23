package org.escaperun.game.model.entities;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.controller.Sound;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.entities.skills.SmasherSkillsContainer;
import org.escaperun.game.model.entities.skills.smasher.Cleave;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Smasher extends Avatar {

    private SmasherSkillsContainer smasherSkills;
    private Timer attackTimer = new Timer(0);

    public Smasher(Position initialPosition) {
        super(new Decal('@', Color.BLACK, Color.RED), initialPosition);
        smasherSkills = new SmasherSkillsContainer(this);
        getStatContainer().getStrength().setBase(10);
        getStatContainer().getAgility().setBase(5);
        getStatContainer().getIntellect().setBase(3);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element avatar = dom.createElement("Smasher");
        super.save(dom, avatar);
        smasherSkills.save(dom, avatar);
        parent.appendChild(avatar);
        return avatar;
    }

    @Override
    public Smasher load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("Smasher") != null && node.getElementsByTagName("Smasher").getLength() > 0)
            us = (Element) node.getElementsByTagName("Smasher").item(0);

        Smasher smash = new Smasher(new Position(0,0));

        Element entityPart = (Element) us.getElementsByTagName("Entity").item(0);
        smash.helperLoad(entityPart);


        Element skills = (Element) us.getElementsByTagName("SmasherSkillsContainer").item(0);
        smash.smasherSkills = new SmasherSkillsContainer(null).load(skills, smash);
        return smash;
    }

    private void helperLoad(Element node) {
        super.load(node);
    }

    public void tick() {
        super.tick();
        attackTimer.tick();
    }

    @Override
    public void playAttackSound() {
        Sound.MELEE.play();
    }

    @Override
    public void attack(Entity defender, Skill skill) {

    }

    @Override
    public void visit(TwoHandedWeapon thw) {
        equipItem(thw);
    }

    @Override
    public void visit(OneHandedWeapon ohw) {
        equipItem(ohw);
    }

    @Override
    public void visit(FistWeapon fw) {
        equipItem(fw);
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
        equipItem(sw);
    }

    public ActiveSkill attemptSkillCast1(Logger log) {
        EquipableItem weap = this.getEquipment().getItemAtSlot(EquipableItem.EquipmentSlot.WEAPON.ordinal());
        if (weap == null) {
            //brawling
            if (attackTimer.getTicksSince() >= 22) {
                attackTimer.reset();
                Cleave clv = new Cleave(0, 0, 0, this, 2, getDirection(), getCurrentPosition(), 15);
                clv.setDecal(new Decal('#', Color.BLACK, Color.RED));
                return clv;
            }
        } else if (weap instanceof OneHandedWeapon) {

            if (attackTimer.getTicksSince() >= 30) {
                attackTimer.reset();
                Cleave clv = new Cleave(0, 0, 0, this, 2, getDirection(), getCurrentPosition(), 15);
                clv.setDecal(new Decal('=', Color.BLACK, Color.RED));
                return clv;
            }
        } else if (weap instanceof TwoHandedWeapon) {

            if (attackTimer.getTicksSince() >= 47) {
                attackTimer.reset();
                Cleave clv = new Cleave(0, 0, 0, this, 2, getDirection(), getCurrentPosition(), 15);
                clv.setDecal(new Decal('-', Color.BLACK, Color.RED));
                return clv;
            }
        } else {
            if (attackTimer.getTicksSince() >= 22) {
                attackTimer.reset();
                Cleave clv = new Cleave(0, 0, 0, this, 2, getDirection(), getCurrentPosition(), 15);
                clv.setDecal(new Decal('#', Color.BLACK, Color.RED));
                return clv;
            }
        }
        //return skill1();
        return null;
    }

    public ActiveSkill skill1(){
        return new Cleave(0, 0, 0, this, 2, getDirection(), getCurrentPosition(), 4);
    }

    public ActiveSkill attemptSkillCast2(Logger log) {
        return null;
    }
    public ActiveSkill attemptSkillCast3(Logger log) {
        return null;
    }

    @Override
    public SmasherSkillsContainer getSkillsContainer() {
        return smasherSkills;
    }
}
