package org.escaperun.game.model.entities.skills.summoner;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.SkillSuccess;

public class Boon extends ActiveSkill {

    private int healAmount = 10;

    public Boon(){
        skillName = "Boon";
        skillLevel = 1;
        this.manaCost = 10;
    }

    public Boon(Entity skillOwner){
        skillName = "Boon";
        skillLevel = 1;
        this.manaCost = 10;
        skillOwner.getStatContainer().getLife().healDamage(healAmount);
    }

    public int getHealAmount() {return healAmount;}

    @Override
    public double getGoodStat() {
        return this.getOwner().getStatContainer().getIntellect().getCurrent();
    }

    @Override
    public double generateSuccess(Entity attacker, Entity defender, int moveAmount) {
        return 0;
    }

    @Override
    public String getName() {
        return skillName;
    }

    @Override
    public int getSkillLevel() {
        return skillLevel;
    }
}
