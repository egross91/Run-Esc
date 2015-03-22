package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.equipment.EquipableItem;

public class EquipmentContainer<T extends EquipableItem> extends ItemContainer<T> {
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

    private T swapItem(int slot, T toEquip) {
        T ret = get(slot);
        add(slot, toEquip);

        return ret;
    }
}
