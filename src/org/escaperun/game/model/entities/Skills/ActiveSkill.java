package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;

public abstract class ActiveSkill extends Skill {

    private int OffensePower;
    private int DefensePower;
    protected int manaCost; //use of this skill will reduce mana by this amount
    protected int skillLevel;
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
    public Entity getOwner(){
        return skillOwner;
    }
    public int getManaCost() {
        return manaCost;
    }
    public abstract SkillSuccess generateSuccess(Entity attacker, Entity defender);
}