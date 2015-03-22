package org.escaperun.game.model.entities.statistics;

public class PrimaryStatistic extends Statistic {

    private Double base;
    private Double additiveDelta;
    private Double multiplicativeDelta;

    @Override
    public void notifyChange() {
        // nop
    }

    @Override
    protected void setBase_internal(Double to) {
        this.base = to;
        this.additiveDelta = 0.0;
        this.multiplicativeDelta = 0.0;
    }

    public Double getBase() {
        return base;
    }

    public Double getCurrent() {
        Double base = (Double) this.base;
        Double additiveDelta = (Double) this.additiveDelta;
        Double val = (multiplicativeDelta*(base-additiveDelta));
        return (Double) val;
    }

    // add a delta to additive modifier
    public void addDelta(Double toAdd) {
        Double next = ((Double) additiveDelta) + ((Double) toAdd);
        if (next > ((Double) additiveDelta)) {
            next = (Double) base;
        }
        additiveDelta = (Double) next;
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
