package org.escaperun.game.model.entities.skills.summoner;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.SkillSuccess;

public class Boon extends ActiveSkill {


    @Override
    public SkillSuccess generateSuccess(Entity attacker, Entity defender) {
        return null;
    }
}
