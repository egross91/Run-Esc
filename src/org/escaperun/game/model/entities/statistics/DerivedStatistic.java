package org.escaperun.game.model.entities.statistics;

public abstract class DerivedStatistic<T extends Number> extends Statistic<T> {

    public DerivedStatistic(T type) {super(type);}

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
    }
}
