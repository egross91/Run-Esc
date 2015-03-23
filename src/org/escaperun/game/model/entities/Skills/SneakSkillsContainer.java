package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.skills.sneak.Creep;
import org.escaperun.game.model.entities.skills.sneak.DetectAndRemoveTrap;
import org.escaperun.game.model.entities.skills.sneak.PickPocket;
import org.escaperun.game.model.items.equipment.weapons.RangedWeapon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class SneakSkillsContainer extends SkillsContainer {
    private PickPocket pickPocket;
    private DetectAndRemoveTrap drt;
    private RangedAttack rangedAttack;
    private Creep creep;
    private int containerSize = 7;

    public SneakSkillsContainer(Entity skillOwner){
        super(skillOwner);
        pickPocket = new PickPocket();
        drt = new DetectAndRemoveTrap();
        creep = new Creep();
        rangedAttack = new RangedAttack();
        skills.add(3, pickPocket);
        skills.add(4, drt);
        skills.add(5, creep);
        skills.add(6, rangedAttack);
    }

    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("SneakSkillsContainer");
        parent.appendChild(us);
        us.setAttribute("PickPocketLvl", Integer.toString(pickPocket.getSkillLevel()));
        us.setAttribute("DrtLvl", Integer.toString(drt.getSkillLevel()));
        us.setAttribute("RangedAttackLvl", Integer.toString(rangedAttack.getSkillLevel()));
        us.setAttribute("CreepLvl", Integer.toString(creep.getSkillLevel()));

        return us;
    }

    public SneakSkillsContainer load(Element node, Entity ent) {
        if (node == null) return null;
        SneakSkillsContainer res = new SneakSkillsContainer(ent);
        res.pickPocket.setSkillLevel(Integer.parseInt(node.getAttribute("PickPocketLvl")));
        res.drt.setSkillLevel(Integer.parseInt(node.getAttribute("DrtLvl")));
        res.rangedAttack.setSkillLevel(Integer.parseInt(node.getAttribute("RangedAttackLvl")));
        res.creep.setSkillLevel(Integer.parseInt(node.getAttribute("CreepLvl")));
        return res;
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

    public RangedAttack getRangedAttack(){return rangedAttack; }

    @Override
    public int getContainerSize(){
        return containerSize;
    }

    @Override
    public ArrayList<Skill> getSkillsArrayList() {
        return skills;
    }
}
