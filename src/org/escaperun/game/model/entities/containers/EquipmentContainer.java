package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.entities.statistics.Statistic;
import org.escaperun.game.model.items.Item;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class EquipmentContainer<T extends EquipableItem> extends ItemContainer<T> implements Saveable {
    private static final int MAX_EQUIPMENT_SLOTS = 6;

    public EquipmentContainer() {
        super(MAX_EQUIPMENT_SLOTS);
    }

    public T getItemAtSlot(int slot) {
        return getItems().get(slot);
    }

    public T equipItem(T toEquip) {
        return swapItem(toEquip.getEquipmentSlot(), toEquip);
    }

    public T unequipItem(int slot) {
        return swapItem(slot, null);
    }

    private ArrayList<Statistic> subs = new ArrayList<Statistic>();

    public void subscribe(Statistic stat) {
        subs.add(stat);
    }

    public void change() {
        for (Statistic st : subs) st.equipmentChange(this);
    }

    private T swapItem(int slot, T toEquip) {
        T ret = get(slot);
        add(slot, toEquip);
        change();
        return ret;
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("EquipmentContainer");
        parent.appendChild(us);

        for (Item i : getItems()) {
            if (i == null) continue;
            i.save(dom, us);
        }

        return us;
    }

    @Override
    public EquipmentContainer load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("EquipmentContainer") != null && node.getElementsByTagName("EquipmentContainer").getLength() > 0)
            us = (Element) node.getElementsByTagName("EquipmentContainer").item(0);

        EquipmentContainer<EquipableItem> ec = new EquipmentContainer();
        NodeList items = us.getElementsByTagName("Item");
        for (int i = 0; i < items.getLength(); i++) {
            Element it = (Element) items.item(i);
            Item load = new Item(Decal.BLANK, "Item", "This is an Item") {

                @Override
                public boolean isCollidable() {
                    return false;
                }
            }.load(it);
            ec.equipItem((EquipableItem) load);
        }
        return ec;
    }
}
