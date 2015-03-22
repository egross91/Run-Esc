package org.escaperun.game.model.entities.statistics;

public class Hardiness extends PrimaryStatistic<Integer> {

    public Hardiness(){
        super(0); // dummy
        setBase(5);
    }

    @Override
    public String getName() {
        return "Hardiness";
    }
}
