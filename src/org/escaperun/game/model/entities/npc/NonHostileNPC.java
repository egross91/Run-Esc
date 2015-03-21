package org.escaperun.game.model.entities.npc;


import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

public class NonHostileNPC extends NPC {

    public NonHostileNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
    }

    @Override
    public void enchant() {
        //TODO: Write enchant() method to make Non-hostile NPC act a certain way if enchanted is successful.
        //Maybe need to add in parameters
    }

    @Override
    public String observe() {
        return null;
    }

    @Override
    public void attack(Entity defender, Skill skil) {
        //TODO: Implement "running away attack" or something of that nature
        // Put in the Behavior for running away.
    }

    @Override
    public void visit(TwoHandedWeapon thw) {
        //TODO
    }

    @Override
    public void visit(OneHandedWeapon ohw) {
        //TODO
    }

    @Override
    public void visit(FistWeapon fw) {
        //TODO
    }

    @Override
    public void visit(BoomstickWeapon bsw) {
        //TODO
    }

    @Override
    public void visit(BowWeapon bw) {
        //TODO
    }

    @Override
    public void visit(ThrowingKnivesWeapon tkw) {
        //TODO
    }

    @Override
    public void visit(StaffWeapon sw) {
        //TODO
    }
}
