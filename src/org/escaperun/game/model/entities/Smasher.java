package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.Skill;
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

    public Smasher(Position initialPosition) {
        super(new Decal('@', Color.BLACK, Color.RED), initialPosition);
    }

    @Override
    public void attack(Entity defender, Skill skill) {

    }

    @Override
    public void visit(TwoHandedWeapon thw) {
        equipWeaponItem(thw);
    }

    @Override
    public void visit(OneHandedWeapon ohw) {
        equipWeaponItem(ohw);
    }

    @Override
    public void visit(FistWeapon fw) {
        equipWeaponItem(fw);
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
        equipWeaponItem(sw);
    }

}
