package org.escaperun.game.model.entities.statistics;

public class PrimaryStatistic<T extends Number> extends Statistic<T> {

    protected Double base;
    protected Double additiveDelta;
    protected Double multiplicativeDelta;

    public PrimaryStatistic(T type) {super(type);}

    @Override
    public void notifyChange() {
        // nop
    }

    @Override
    protected void setBase_internal(T to) {
        this.base = to.doubleValue();
        this.additiveDelta = 0.0;
        this.multiplicativeDelta = 1.0;
    }

    public T getBase() {
        if (isInteger) return (T)  (Integer) base.intValue();
        else return (T) base;
    }

    public T getCurrent() {
        Double val = (multiplicativeDelta*(base-additiveDelta));
        if (isInteger) return (T) (Integer) val.intValue();
        else return (T) val;
    }

    @Override
    public String getName() {
        return "PrimaryStatistics";
    }

    // add a delta to additive modifier
    public void addDelta(T toAdd) {
        Double next = ((Double) additiveDelta) + ((Double) toAdd);
        if (next > ((Double) additiveDelta)) {
            next = (Double) base;
        }
    }

    // add a percentage to percentage modifier
    public void multiplyDelta(Double toMultiply) {
        if (toMultiply < 0.0 || toMultiply > 1.0)
            throw new IllegalArgumentException("tomultiply " + toMultiply + " must be between 0.0 and 1.0 inclusive!");

        Double res = multiplicativeDelta+toMultiply;
        if (res > 1.0) res = 1.0;

        multiplicativeDelta = res;
    }
}