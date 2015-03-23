package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.entities.skills.SmasherSkillsContainer;
import org.escaperun.game.model.entities.skills.smasher.Cleave;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class Smasher extends Avatar{

    private SmasherSkillsContainer smasherSkills;

    public Smasher(Position initialPosition) {
        super(new Decal('@', Color.BLACK, Color.RED), initialPosition);
        smasherSkills = new SmasherSkillsContainer(this);
        getStatContainer().getStrength().setBase(10);
        getStatContainer().getAgility().setBase(5.0);
        getStatContainer().getIntellect().setBase(3);

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

    @Override
    public Projectile skill1(){
        return new Cleave(0, 0, 0, this, 3, getDirection(), getCurrentPosition(), 2);
    }

    @Override
    public SmasherSkillsContainer getSkillsContainer() {
        return smasherSkills;
    }
}
