package org.escaperun.game.model.entities.skills;

public abstract class ActiveSkill extends Skill {

    private int OffensePower;
    private int DefensePower;

    //private Statistic(stat_enum) beneficialStat
    // stats not implemented yet.

    public ActiveSkill(){
        this.DefensePower = 0;
        this.OffensePower = 0;
        //this.beneficialStat = "Attack";
    }
    public ActiveSkill(int ofp, int dfp /*Statistic(stat_enum) beneficialStat */){
        this.DefensePower = dfp;
        this.OffensePower = ofp;
        //this.beneficialStat = beneficialStat;
    }
    /*
    @Override
    public Statistic(stat_enum) getBeneficialStat() {
        return beneficialStat;
    }
    */
}