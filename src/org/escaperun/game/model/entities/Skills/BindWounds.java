package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.npc.NPC;

public class BindWounds extends ActiveSkill {

    @Override
    public SkillSuccess generateSuccess(Avatar avatar, NPC npc) {
        return null;
    }
}
