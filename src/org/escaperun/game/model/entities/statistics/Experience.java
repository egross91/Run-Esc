package org.escaperun.game.model.entities.statistics;

import java.lang.Long;
public class Experience extends PrimaryStatistic{
    private Double maxExp;

    public Experience(){
        setBase(new Double(0.0));
        this.maxExp = 0.0;
    }
    public Experience(Double maxExp){
        setBase(new Double(0.0));
        this.maxExp = maxExp;
    }
    public void setMaxExp(Double maxExp){
        this.maxExp = maxExp;
    }
    public Double getMaxExp(){
        return maxExp;
    }

}
