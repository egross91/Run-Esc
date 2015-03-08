package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.TakeableItem;

import java.util.List;

public class InventoryContainer<T extends TakeableItem> extends ItemContainer<T> {

    public InventoryContainer() {
        super();
    }

    public InventoryContainer(int capacity) {
        super(capacity);
    }

    public InventoryContainer(List<T> items, int capacity) {
        super(items, capacity);
    }

    public void add(T item) {
        add(getItems().size(), item);
    }

    public void add(int index, T item) {
        if (!isFull()) {
            getItems().add(index, item);
        }
    }

    public void remove(int index) {
        getItems().remove(index);
    }

    private boolean isFull() {
        return getItems().size() + 1 > getCapacity();
    }
}
