package org.escaperun.game.model.entities.skills.summoner;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.SkillSuccess;

public class Enchant extends ActiveSkill {

    @Override
    public SkillSuccess generateSuccess(Avatar avatar, NPC npc) {
        return null;
    }
}
