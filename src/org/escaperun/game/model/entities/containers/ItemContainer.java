package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.Item;
import org.escaperun.game.model.items.TakeableItem;

import java.util.ArrayList;
import java.util.List;

public class ItemContainer<T extends TakeableItem> {

    private ArrayList<T> items;
    private final int MAX_CAPACITY;

    public ItemContainer() {
        this(30);
    }

    public ItemContainer(int capacity) {
        this.MAX_CAPACITY = capacity;
        this.items = new ArrayList<T>(MAX_CAPACITY);
    }

    public int getCapacity() {
        return this.MAX_CAPACITY;
    }

    protected ArrayList<T> getItems() {
        return this.items;
    }

    public void add(T item) {
        add(items.size(), item);
    }

    public void add(int index, T item) {
        if (!isFull() && indexOk(index)) {
            items.add(index, item);
        }
    }

    public T remove(int index) {
        return items.remove(index);
    }

    public T get(int index) {
        if (index >= items.size()) return null;
        return items.get(index);
    }

    protected boolean indexOk(int index) {
        return index < MAX_CAPACITY && index > -1;
    }

    protected boolean isFull() {
        return items.size() + 1 > MAX_CAPACITY;
    }
}
