package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.skills.sneak.Creep;
import org.escaperun.game.model.entities.skills.sneak.DetectAndRemoveTrap;
import org.escaperun.game.model.entities.skills.sneak.PickPocket;
import org.escaperun.game.model.items.equipment.weapons.RangedWeapon;

import java.util.ArrayList;

public class SneakSkillsContainer extends SkillsContainer {
    private PickPocket pickPocket;
    private DetectAndRemoveTrap drt;
    private Creep creep;
    private RangedWeapon rangedWeapon;
    private int containerSize = 7;

    public SneakSkillsContainer(Entity skillOwner){
        super(skillOwner);
        pickPocket = new PickPocket();
        drt = new DetectAndRemoveTrap();
        creep = new Creep();
        //rangedWeapon = new RangedWeapon();
        skills.add(3, pickPocket);
        skills.add(4, drt);
        skills.add(5, creep);
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

    @Override
    public int getContainerSize(){
        return containerSize;
    }

    @Override
    public ArrayList<ActiveSkill> getSkillsArrayList() {
        return skills;
    }
}
