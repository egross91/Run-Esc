package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TwoHandedAttack extends ActiveSkill {

    public TwoHandedAttack(){
        skillName = "Two Handed Attack";
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
