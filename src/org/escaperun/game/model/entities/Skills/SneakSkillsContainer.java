package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.skills.sneak.Creep;
import org.escaperun.game.model.entities.skills.sneak.DetectAndRemoveTrap;
import org.escaperun.game.model.entities.skills.sneak.PickPocket;
import org.escaperun.game.model.items.equipment.weapons.RangedWeapon;

public class SneakSkillsContainer extends SkillsContainer {
    PickPocket pickPocket;
    DetectAndRemoveTrap drt;
    Creep creep;
    RangedWeapon rangedWeapon;

    public SneakSkillsContainer(Entity skillOwner){
        super(skillOwner);
        pickPocket = new PickPocket();
        drt = new DetectAndRemoveTrap();
        creep = new Creep();
        //rangedWeapon = new RangedWeapon();
    }

    public PickPocket getPickPocket(){
        return pickPocket;
    }

    public DetectAndRemoveTrap getDetectAndRemoveTrap(){
        return drt;
    }

    public Creep getCreep(){
        return creep;
    }
}
