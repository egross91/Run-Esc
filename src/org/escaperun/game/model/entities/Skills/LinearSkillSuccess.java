package org.escaperun.game.model.entities.skills;


import org.escaperun.game.model.entities.Entity;

public class LinearSkillSuccess extends SkillSuccess {
    private Skill avatarSkill;
    private Skill npcSkill;
    private static double maxSkillLevelContribution = 50;
    private static double maxStatContribution = 50;
    private static double maxDefenseRating = 10;


    public static double generateSkillSuccess(Entity attacker, Entity Defender, ActiveSkill p, double AttStat, int moveAmount){
        double offRating = ((AttStat / 100) * maxStatContribution) + ((p.getSkillLevel() / 10) * maxSkillLevelContribution);
        double defenseRating = getDefendingStats(Defender, p) / maxDefenseRating;
        double attOut = (offRating - defenseRating) * p.getOffensePower();

        double thruput = calcDistance(moveAmount, (attOut- Defender.getStatContainer().getArmorRating().getCurrent()));

        //armour rating should be constant amount so it is deducted after we get what the attack should deal.
        return thruput;
    }

    private static double calcDistance(int mov, double thru){
        double max  = thru / 30;
        return (thru - (max * (double)mov));
    }

    private static double getDefendingStats(Entity defender, ActiveSkill p){
        //account for weakness with reference to p
        double defenseRating = defender.getStatContainer().getDefensiveRating().getCurrent();
        // if(p.getDamageType == defender.weakness)
        // defenseRating*
        return defenseRating;

    }

}
