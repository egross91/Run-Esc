package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.EquipableItem;

import java.util.List;

public class EquipmentContainer<T extends EquipableItem> extends ItemContainer<T> {
    public EquipmentContainer() {
        super(5);
    }

    public EquipmentContainer(List<T> equipables) {
        super(equipables, 5);
    }

    public T equipItem(T equipable) {
        return swap(equipable.getEquipmentSlot(), equipable);
    }

    public T removeItem(int slot) {
        return swap(slot, null);
    }
}
