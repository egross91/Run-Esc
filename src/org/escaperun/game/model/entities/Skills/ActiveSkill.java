package org.escaperun.game.model.entities.skills;

import com.sun.org.glassfish.external.statistics.Statistic;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;

public abstract class ActiveSkill extends Skill {

    private int OffensePower;
    private int DefensePower;
    protected int manaCost; //use of this skill will reduce mana by this amount
    protected int skillLevel;
    protected String skillName;
    private Entity skillOwner;

    public ActiveSkill(){
        this.DefensePower = 0;
        this.OffensePower = 0;
        this.skillLevel = 0;
        this.manaCost = 0;
    }
    public ActiveSkill(int ofp, int dfp, int skillLevel, Entity skillOwner ){
        this.DefensePower = dfp;
        this.OffensePower = ofp;
        this.skillLevel = skillLevel;
        this.skillOwner = skillOwner;
        this.manaCost = 0;
    }
    public void incrementSkillLevel(){
        skillLevel++;
    }
    public Entity getOwner(){
        return skillOwner;
    }
    public int getManaCost() {
        return manaCost;
    }
    public int getSkillLevel(){
        return this.skillLevel;
    }
    public abstract double getGoodStat();

    public abstract double generateSuccess(Entity attacker, Entity defender, int moveAmount);

    public double getOffensePower(){
        return (double)this.OffensePower;
    }
    public double getDefensePower(){
        return (double)this.DefensePower;
    }

    public abstract String getName();
}