package org.escaperun.game.model.entities.statistics;

import java.util.ArrayList;

public abstract class Statistic {

    private ArrayList<Statistic> subscribers = new ArrayList<Statistic>();

    public void subscribe(Statistic stat) {
        subscribers.add(stat);
    }

    public void setBase(Double to) {
        setBase_internal(to);
        for (Statistic subscriber : subscribers) {
            subscriber.notifyChange();
        }
    }

    // called when a subscribed-to statistic has changed
    // it basically tells a statistic that it must be updated
    public abstract void notifyChange();

    protected abstract void setBase_internal(Double to);
    public abstract Double getBase();
    public abstract Double getCurrent();
}
