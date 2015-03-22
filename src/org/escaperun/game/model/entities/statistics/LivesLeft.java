package org.escaperun.game.model.entities.statistics;

public class LivesLeft extends PrimaryStatistic<Integer> {

    public LivesLeft(){
        super(0);
        setBase(5);
    }

    @Override
    public String getName() {
        return "LivesLeft";
    }
}
