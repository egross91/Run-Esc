package org.escaperun.game.model.entities.statistics;

public class PrimaryStatistic<T extends Number> extends Statistic<T> {

    private T base;
    private T additiveDelta;
    private Double multiplicativeDelta;

    @Override
    public void notifyChange() {
        // nop
    }

    @Override
    protected void setBase_internal(T to) {
        this.base = to;
        this.additiveDelta = (T) (Integer) 0;
        this.multiplicativeDelta = 0.0;
    }

    public T getBase() {
        return base;
    }

    public T getCurrent() {
        Double base = (Double) this.base;
        Double additiveDelta = (Double) this.additiveDelta;
        Double val = (multiplicativeDelta*(base-additiveDelta));
        return (T) val;
    }

    // add a delta to additive modifier
    public void addDelta(T toAdd) {
        Double next = ((Double) additiveDelta) + ((Double) toAdd);
        if (next > ((Double) additiveDelta)) {
            next = (Double) base;
        }
        additiveDelta = (T) next;
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
