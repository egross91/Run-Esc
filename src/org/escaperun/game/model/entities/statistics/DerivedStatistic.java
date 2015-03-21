package org.escaperun.game.model.entities.statistics;

import java.util.ArrayList;

public abstract class DerivedStatistic<T extends Number> extends Statistic<T> {

    //private ArrayList<PrimaryStatistic> subscribedTo = new ArrayList<PrimaryStatistic>();


    @Override
    public void setBase(T to) {
        // nop
    }

    @Override
    public void notifyChange() {
        recalculate();
    }

    protected abstract void recalculate();

    @Override
    protected void setBase_internal(T to) {
        // nop
        //Don't know if want to reset stuff
    }
}
