package org.escaperun.game.model.entities.statistics;

public class Experience extends PrimaryStatistic<Integer> {
    private Integer maxExp;

    public Experience(){
        this(0);
    }

    public Experience(Integer maxExp){
        super(0); // dummy
        setBase(0);
        this.maxExp = maxExp;
    }
    public void setMaxExp(Integer maxExp){
        this.maxExp = maxExp;
    }
    public Integer getMaxExp(){
        return maxExp;
    }

    @Override
    public String getName() {
        return "Experience";
    }
}
