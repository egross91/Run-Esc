package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.EquipableItem;

import java.util.List;

public class EquipmentContainer<T extends EquipableItem> extends ItemContainer<T> {
    private static final int MAX_EQUIPMENT_SLOTS = 5;

    public EquipmentContainer() {
        super(MAX_EQUIPMENT_SLOTS);
    }

    public EquipmentContainer(List<T> equipables) {
        super(equipables, MAX_EQUIPMENT_SLOTS);
    }

    public T getEquipItemAtSlot(EquipableItem.EquipmentSlot slot) {
        return getItems().get(slot.getSlot());
    }

    public T equipItem(T equipable) {
        int slot = equipable.getEquipmentSlot();

        T item = getItems().remove(slot);
        getItems().add(slot, equipable);

        return item;
    }
}
