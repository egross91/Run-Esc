package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.*;
import org.escaperun.game.model.entities.npc.NPC;

/**
 * This class will return a value from 0 to 100 that will denote the value % of MAX_VALUE
 */

public final class SkillSuccessGenerator {

    int[] skillBonus = {7, 9, 11, 13, 15, 17, 19, 21, 23, 25};      //contains bonus chance for higher skill levels


    public int calcSuccess(Avatar avatar, NPC npc, Skill skill){
       // int avatarSkillLevel = avatar.getAvatarStatistic(skill.getBeneficialStat());



        return 0;
    }

}
