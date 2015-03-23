package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Bargain extends ActiveSkill {

    public Bargain(){
        skillName = "Bargain";
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
    public int getSkillLevel() {
        return skillLevel;
    }
}
