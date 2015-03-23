package org.escaperun.game.model.entities.skills.sneak;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.SkillSuccess;

public class DetectAndRemoveTrap extends ActiveSkill {

    public DetectAndRemoveTrap(){
        skillName = "Detect and Remove Trap";
        skillLevel = 1;
    }

    @Override
    public double getGoodStat() {
        return this.getOwner().getStatContainer().getAgility().getCurrent();
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
