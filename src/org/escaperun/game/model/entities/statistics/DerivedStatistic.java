package org.escaperun.game.model.entities.statistics;

public abstract class DerivedStatistic<T extends Number> extends Statistic<T> {

    @Override
    public void setBase(T to) {
        // nop
    }

    @Override
    protected void setBase_internal(T to) {
        // nop
    }
}
