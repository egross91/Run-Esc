package org.escaperun.game.model.entities.skills.summoner;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.SkillSuccess;

public class Enchant extends ActiveSkill {

    public Enchant(){
        skillName = "Enchant";
        skillLevel = 1;
    }

    @Override
    public double getGoodStat() {
        return this.getOwner().getStatContainer().getIntellect().getCurrent();
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
