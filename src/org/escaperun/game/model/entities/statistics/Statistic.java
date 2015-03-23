package org.escaperun.game.model.entities.statistics;

import java.util.ArrayList;

public abstract class Statistic<T extends Number> implements IStatSubscriber{

    private ArrayList<IStatSubscriber> subscribers = new ArrayList<IStatSubscriber>();

    public Statistic(T type) {
        if (type == null) throw new IllegalArgumentException("type must not be null");
        isInteger = type instanceof Integer;
    }

    public void subscribe(IStatSubscriber stat) {
        subscribers.add(stat);
    }

    public void unsubscribe(IStatSubscriber stat) { subscribers.remove(stat);}

    public final void setBase(T to) {
        setBase_internal(to);
        for (IStatSubscriber subscriber : subscribers) {
            subscriber.notifyChange();
        }
    }

    // called when a subscribed-to statistic has changed
    // it basically tells a statistic that it must be updated
    public abstract void notifyChange();

    protected abstract void setBase_internal(T to);
    public abstract T getBase();
    public abstract T getCurrent();
    protected boolean isInteger;
    public abstract String getName();
}