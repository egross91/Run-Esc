package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.Item;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ItemContainer<T extends TakeableItem> implements Saveable {

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

    protected int getFirstEmptySlot() {
        for (int i = 0; i < MAX_CAPACITY; ++i) {
            if (items.get(i) == null) {
                return i;
            }
        }

        throw new RuntimeException("Should never happen, because full checks should happen.");
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("ItemContainer");
        parent.appendChild(us);

        for (Item i : getItems()) {
            if (i == null) continue;
            i.save(dom, us);
        }

        return us;
    }

    @Override
    public ItemContainer load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("ItemContainer") != null && node.getElementsByTagName("ItemContainer").getLength() > 0)
            us = (Element) node.getElementsByTagName("ItemContainer").item(0);

        ItemContainer<TakeableItem> ec = new ItemContainer();
        NodeList items = us.getElementsByTagName("Item");
        for (int i = 0; i < items.getLength(); i++) {
            Element it = (Element) items.item(i);
            Item load = new Item(Decal.BLANK) {

                @Override
                public boolean isCollidable() {
                    return false;
                }
            }.load(it);
            ec.add((TakeableItem) load);
        }
        return ec;
    }
}
