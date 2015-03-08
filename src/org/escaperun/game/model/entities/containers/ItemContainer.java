package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemContainer<T extends Item> {
    private ArrayList<T> items;
    private final int MAX_CAPACTIY;

    public ItemContainer() {
        this(30);
        this.items = new ArrayList<T>(MAX_CAPACTIY);
    }

    public ItemContainer(int capacity) {
        this.MAX_CAPACTIY = capacity;
        this.items = new ArrayList<T>(MAX_CAPACTIY);
    }

    public ItemContainer(List<T> container, int capacity) {
        this(capacity);
        this.items = new ArrayList<T>(container);
    }

    public int getCapacity() {
        return this.MAX_CAPACTIY;
    }

    public ArrayList<T> getItems() {
        return this.items;
    }

    public T swap(int index, T item) {
        if (indexOk(index)) {
            T ret = items.get(index);
            items.set(index, item);
            return ret;
        }

        throw new IllegalArgumentException("the use of ItemContainer is wrong");
    }

    protected boolean indexOk(int index) {
        return index < MAX_CAPACTIY && index > -1;
    }
}
