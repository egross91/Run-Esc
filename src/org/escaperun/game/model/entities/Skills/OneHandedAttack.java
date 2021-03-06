package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OneHandedAttack extends ActiveSkill {

    public OneHandedAttack(){
        skillName = "One Handed Attack";
        skillLevel = 1;
    }

    @Override
    public double getGoodStat() {
        return 0;
    }

    @Override
    public double generateSuccess(Entity attacker, Entity defender, int moveAmoutn) {
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
