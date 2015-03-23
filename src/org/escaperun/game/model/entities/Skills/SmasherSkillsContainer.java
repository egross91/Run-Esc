package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.view.Decal;

import java.awt.*;
import java.util.ArrayList;

public class SmasherSkillsContainer extends SkillsContainer {

    private OneHandedWeapon ohw;
    private TwoHandedWeapon thw;
    private FistWeapon fist;
    private int containerSize = 6;


    public SmasherSkillsContainer(Entity skillOwner){
        super(skillOwner);
        ohw = new OneHandedWeapon(new Decal('t', Color.BLACK, Color.BLUE), "The Annihilator", "A weapon of mass destruction.");
        thw = new TwoHandedWeapon(new Decal('T', Color.BLACK, Color.BLUE), "The Beheader", "Used by a fallen executioner.");
        fist = new FistWeapon(new Decal('F', Color.BLACK, Color.BLUE), "Spiked Knuckles", "Gloves with nails pasted on them.");
        //skills.add(3, ohw);
        //skills.add(4, thw);
        //skills.add(5, fist);
    }

    public OneHandedWeapon getOneHandedWeapon(){
        return ohw;
    }

    public TwoHandedWeapon getTwoHandedWeapon(){
        return thw;
    }

    public FistWeapon getFistWeapon(){
        return fist;
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
