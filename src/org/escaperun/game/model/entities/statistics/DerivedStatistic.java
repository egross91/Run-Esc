package org.escaperun.game.model.entities.statistics;

public abstract class DerivedStatistic<T extends Number> extends Statistic<T> {

    private Double base;

    public DerivedStatistic(T type) {super(type);}

    @Override
    public void setBase(T to) {
        // nop
    }

    @Override
    public T getBase() {
        return (T) base;
    }

    public T getCurrent() {
        return (T) base;
    }

    @Override
    public void notifyChange() {
        recalculate();
    }

    protected abstract void recalculate();

    @Override
    protected void setBase_internal(T to) {
        this.base = to.doubleValue();
    }
}
