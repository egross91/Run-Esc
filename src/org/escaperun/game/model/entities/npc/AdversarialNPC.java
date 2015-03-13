package org.escaperun.game.model.entities.npc;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

public class AdversarialNPC extends NPC {

    public AdversarialNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
    }

    @Override
    public void enchant() {
        //TODO: Write enchant() method to make Adversarial NPC act a certain way if enchanted is successful.
    }

    @Override
    public void visit(TwoHandedWeapon thw) {

    }

    @Override
    public void visit(OneHandedWeapon ohw) {

    }

    @Override
    public void visit(FistWeapon fw) {

    }

    @Override
    public void visit(BoomstickWeapon bsw) {

    }

    @Override
    public void visit(BowWeapon bw) {

    }

    @Override
    public void visit(ThrowingKnivesWeapon tkw) {

    }

    @Override
    public void visit(StaffWeapon sw) {

    }
}
