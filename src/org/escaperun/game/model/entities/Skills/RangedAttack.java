package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;

public class RangedAttack extends ActiveSkill {

    public RangedAttack(){
        skillName = "Ranged Attack";
        skillLevel = 1;
    }

    @Override
    public double getGoodStat() {
        return 0;
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
    public int getSkillLevel(){
        return skillLevel;
    }
}
