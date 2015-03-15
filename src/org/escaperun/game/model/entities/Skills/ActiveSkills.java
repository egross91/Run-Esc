package org.escaperun.game.model.entities.Skills;

import com.sun.org.glassfish.external.statistics.Statistic;

/**
 * Created by TubbyLumpkins on 3/10/15.
 */
public abstract class ActiveSkills {

    private int OffensePower;
    private int DefensePower;

    //private Statistic(stat_enum) beneficialStat
    // stats not implemented yet.

    public ActiveSkills(){
        this.DefensePower = 0;
        this.OffensePower = 0;
    }
    public ActiveSkills(int ofp, int dfp){
        this.DefensePower = dfp;
        this.OffensePower = ofp;
    }


}