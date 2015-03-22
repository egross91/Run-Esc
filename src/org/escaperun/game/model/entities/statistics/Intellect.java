package org.escaperun.game.model.entities.statistics;

public class Intellect extends PrimaryStatistic<Integer> {
    public Intellect(){

        super(0); // dummy
        setBase(5);
    }

    @Override
    public String getName() {
        return "Intellect";
    }
}
