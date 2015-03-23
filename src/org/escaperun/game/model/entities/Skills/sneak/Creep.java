package org.escaperun.game.model.entities.skills.sneak;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.SkillSuccess;

public class Creep extends ActiveSkill {
    @Override
    public double getGoodStat() {
        return this.getOwner().getStatContainer().getAgility().getCurrent();
    }

    @Override
    public double generateSuccess(Entity attacker, Entity defender) {
        return 0;
    }
}
