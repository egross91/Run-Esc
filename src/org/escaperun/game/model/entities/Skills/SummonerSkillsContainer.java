package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.skills.summoner.Bane;
import org.escaperun.game.model.entities.skills.summoner.Boon;
import org.escaperun.game.model.entities.skills.summoner.Enchant;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class SummonerSkillsContainer extends SkillsContainer {

    Enchant enchant;
    Boon boon;
    Bane bane;
    StaffWeapon staff;

    public SummonerSkillsContainer(){
        super();
        enchant = new Enchant();
        boon = new Boon();
        bane = new Bane();
        staff = new StaffWeapon(new Decal('S', Color.BLACK, Color.BLUE));
    }

    public Enchant getEnchant(){
        return enchant;
    }

    public Boon getBoon(){
        return boon;
    }

    public Bane getBane(){
        return bane;
    }

    public StaffWeapon getStaffWeapon(){
        return staff;
    }
}
