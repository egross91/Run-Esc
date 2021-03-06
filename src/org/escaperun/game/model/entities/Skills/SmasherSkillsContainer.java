package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.util.ArrayList;

public class SmasherSkillsContainer extends SkillsContainer {

    //private OneHandedWeapon ohw;
    //private TwoHandedWeapon thw;
    //private FistWeapon fist;

    private OneHandedAttack oha;
    private TwoHandedAttack tha;
    private Brawling brawling;

    private int containerSize = 6;


    public SmasherSkillsContainer(Entity skillOwner){
        super(skillOwner);
        oha = new OneHandedAttack();
        tha = new TwoHandedAttack();
        brawling = new Brawling();
        /*
        ohw = new OneHandedWeapon(new Decal('t', Color.BLACK, Color.BLUE), "The Annihilator", "A weapon of mass destruction.");
        thw = new TwoHandedWeapon(new Decal('T', Color.BLACK, Color.BLUE), "The Beheader", "Used by a fallen executioner.");
        fist = new FistWeapon(new Decal('F', Color.BLACK, Color.BLUE), "Spiked Knuckles", "Gloves with nails pasted on them.");
        */
        skills.add(3, oha);
        skills.add(4, tha);
        skills.add(5, brawling);
    }

    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("SmasherSkillsContainer");
        parent.appendChild(us);
        us.setAttribute("OhaLvl", Integer.toString(oha.getSkillLevel()));
        us.setAttribute("ThaLvl", Integer.toString(tha.getSkillLevel()));
        us.setAttribute("BrawlingLvl", Integer.toString(brawling.getSkillLevel()));

        return us;
    }

    public SmasherSkillsContainer load(Element node, Entity ent) {
        if (node == null) return null;
        SmasherSkillsContainer res = new SmasherSkillsContainer(ent);
        res.oha.setSkillLevel(Integer.parseInt(node.getAttribute("OhaLvl")));
        res.tha.setSkillLevel(Integer.parseInt(node.getAttribute("ThaLvl")));
        res.brawling.setSkillLevel(Integer.parseInt(node.getAttribute("BrawlingLvl")));

        return res;
    }

    public OneHandedAttack getOneHandedAttack(){
        return oha;
    }

    public TwoHandedAttack getTwoHandedAttack(){
        return tha;
    }

    public Brawling getFistAttack(){
        return brawling;
    }

    @Override
    public int getContainerSize(){
        return containerSize;
    }

    @Override
    public ArrayList<Skill> getSkillsArrayList() {
        return skills;
    }
}
