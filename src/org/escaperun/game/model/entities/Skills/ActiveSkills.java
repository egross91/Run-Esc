package org.escaperun.game.model.entities.Skills;

import com.sun.org.glassfish.external.statistics.Statistic;

/**
 * Created by TubbyLumpkins on 3/10/15.
 */
public abstract class ActiveSkills extends Skills {

    private int OffensePower;
    private int DefensePower;

    //private Statistic(stat_enum) beneficialStat
    // stats not implemented yet.

    public ActiveSkills(){
        this.DefensePower = 0;
        this.OffensePower = 0;
        //this.beneficialStat = "Attack";
    }
    public ActiveSkills(int ofp, int dfp /*Statistic(stat_enum) beneficialStat */){
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