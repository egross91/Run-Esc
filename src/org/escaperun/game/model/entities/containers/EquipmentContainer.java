package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.EquipableItem;
import org.escaperun.game.model.items.MagicItem;
import org.escaperun.game.model.items.MeleeItem;
import org.escaperun.game.model.items.RangeItem;

import java.util.List;

public class EquipmentContainer<T extends EquipableItem> extends ItemContainer<T> {
    private static final int MAX_EQUIPMENT_SLOTS = 5;

    public EquipmentContainer() {
        super(MAX_EQUIPMENT_SLOTS);
    }

    public EquipmentContainer(List<T> equipables) {
        super(equipables, MAX_EQUIPMENT_SLOTS);
    }

    public T getEquipmentItemAtSlot(int slot) {
        return getItems().get(slot);
    }

    public T equipItem(T toEquip) {
        T equipped = swapItem(toEquip.getEquipmentSlot(), toEquip);

        return equipped;
    }

    public T unequipItem(int slot) {
        T equipped = swapItem(slot, null);

        return equipped;
    }

    private T swapItem(int slot, T toEquip) {
        T ret = remove(slot);
        add(slot, toEquip);

        return ret;
    }
}
