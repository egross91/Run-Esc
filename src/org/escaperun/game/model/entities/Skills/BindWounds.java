package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;

public class BindWounds extends ActiveSkill {

    @Override
    public double getGoodStat() {
        return 0;
    }

    @Override
    public SkillSuccess generateSuccess(Entity attacker, Entity defender) {
        return null;
    }
}
