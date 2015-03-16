package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Skills.Skills;

/**
 * Created by TubbyLumpkins on 3/10/15.
 */
public abstract class PassiveSkills extends Skills{
    @Override
    public String getBeneficialStat() {
        return null;
    }
}
