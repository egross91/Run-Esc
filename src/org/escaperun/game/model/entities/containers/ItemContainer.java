package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.TakeableItem;

import java.util.ArrayList;

public class ItemContainer<T extends TakeableItem> {

    private ArrayList<T> items;
    private final int MAX_CAPACITY;

    public ItemContainer() {
        this(30);
    }

    public ItemContainer(int capacity) {
        this.MAX_CAPACITY = capacity;
        this.items = new ArrayList<T>(MAX_CAPACITY);
        for (int i = 0; i < MAX_CAPACITY; ++i) {
            items.add(null);
        }
    }

    public int getCapacity() {
        return this.MAX_CAPACITY;
    }

    protected ArrayList<T> getItems() {
        return this.items;
    }

    public void add(T item) {
        add(getFirstEmptySlot(), item);
    }

    public void add(int index, T item) {
        if (!isFull() && indexOk(index)) {
            items.set(index, item);
        }
    }

    public T get(int index) {
        if (index >= MAX_CAPACITY) return null;
        return items.get(index);
    }

    public void remove(TakeableItem item) {
        int i = 0;
        for (TakeableItem current : items) {
            if (current == null) continue;

            if (current.equals(item)) {
                break;
            }
            ++i;
        }

        items.set(i, null);
    }

    public boolean contains(TakeableItem item) {
        return items.contains(item);
    }

    protected boolean indexOk(int index) {
        return index < MAX_CAPACITY && index > -1;
    }

    public boolean isFull() {
        return getCount() + 1 > MAX_CAPACITY;
    }

    private int getCount() {
        int count = 0;
        for (TakeableItem current : items) {
            if (current != null) {
                ++count;
            }
        }

        return count;
    }

    private int getFirstEmptySlot() {
        int i = 0;
        for (TakeableItem current : items) {
            if (current == null) {
                break;
            }
        }

        return i;
    }
}
