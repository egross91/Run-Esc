package org.escaperun.game.model.entities.statistics;

import java.lang.Long;
public class Experience extends PrimaryStatistic<Long>{
    private long maxExp;

    public Experience(){
        setBase(new Long(0));
        this.maxExp = 0;
    }
    public Experience(Long maxExp){
        setBase(new Long(0));
        this.maxExp = maxExp;
    }
    public void setMaxExp(Long maxExp){
        this.maxExp = maxExp;
    }
    public Long getMaxExp(){
        return maxExp;
    }

}
