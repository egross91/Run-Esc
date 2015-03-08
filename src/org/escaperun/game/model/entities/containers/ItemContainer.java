package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemContainer<T extends Item> {
    private ArrayList<T> items;
    private final int MAX_CAPACITY;

    public ItemContainer() {
        this(30);
        this.items = new ArrayList<T>(MAX_CAPACITY);
    }

    public ItemContainer(int capacity) {
        this.MAX_CAPACITY = capacity;
        this.items = new ArrayList<T>(MAX_CAPACITY);
    }

    public ItemContainer(List<T> container, int capacity) {
        this(capacity);
        this.items = new ArrayList<T>(container);
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
        return items.get(index);
    }

    protected boolean indexOk(int index) {
        return index < MAX_CAPACITY && index > -1;
    }

    protected boolean isFull() {
        return items.size() + 1 > MAX_CAPACITY;
    }
}
