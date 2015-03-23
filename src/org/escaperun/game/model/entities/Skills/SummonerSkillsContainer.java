package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.skills.summoner.Bane;
import org.escaperun.game.model.entities.skills.summoner.Boon;
import org.escaperun.game.model.entities.skills.summoner.Enchant;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

import java.awt.*;
import java.util.ArrayList;

public class SummonerSkillsContainer extends SkillsContainer {

    private Enchant enchant;
    private Boon boon;
    private Bane bane;
    private StaffWeapon staff;
    private int containerSize = 7;

    public SummonerSkillsContainer(Entity skillOwner){
        super(skillOwner);
        enchant = new Enchant();
        boon = new Boon();
        bane = new Bane(0,0,0,skillOwner,0,Direction.EAST,new Position(0,0),5);
        staff = new StaffWeapon(new Decal('S', Color.BLACK, Color.BLUE), "Aurora Staff", "Once used by a monk who became possessed by a Nine-Tailed Fox.");
        skills.add(3, enchant);
        skills.add(4, boon);
        skills.add(5, bane);
        //skills.add(6, staff);
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

    @Override
    public int getContainerSize(){
        return containerSize;
    }

    @Override
    public ArrayList<Skill> getSkillsArrayList() {
        return skills;
    }
}
