package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;

public class BindWounds extends ActiveSkill {

    public BindWounds(){
        skillName = "Bind Wounds";
        skillLevel = 1;
    }

    @Override
    public double getGoodStat() {
        return 0;
    }

    @Override
    public double generateSuccess(Entity attacker, Entity defender) {
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
