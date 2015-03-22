package org.escaperun.game.model.entities.statistics;

import java.util.ArrayList;

public abstract class DerivedStatistic extends Statistic {

    @Override
    public void setBase(Double to) {
        // nop
    }

    @Override
    public void notifyChange() {
        recalculate();
    }

    protected abstract void recalculate();

    @Override
    protected void setBase_internal(Double to) {
        // nop
        //Don't know if want to reset stuff
    }
}
