package org.escaperun.game.model.entities.containers;

import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;

import java.util.List;

public class EquipmentContainer<A extends ArmorItem, W extends WeaponItem> extends ItemContainer<A> {
    private static final int MAX_EQUIPMENT_SLOTS = 5;
    private W weapon;

    public EquipmentContainer() {
        super(MAX_EQUIPMENT_SLOTS);
    }

    public A getArmorItemAtSlot(int slot) {
        return getItems().get(slot);
    }

    public W getWeapon() {
        return this.weapon;
    }

    public W uneqipWeapon() {
        W equipped = weapon;
        this.weapon = null;

        return equipped;
    }

    public W equipWeapon(W toEquip) {
        W equipped = weapon;
        weapon = toEquip;

        return equipped;
    }

    public A equipArmor(A toEquip) {
        return swapItem(toEquip.getEquipmentSlot().getSlot(), toEquip);
    }

    public A unequipArmor(int slot) {
        return swapItem(slot, null);
    }

    private A swapItem(int slot, A toEquip) {
        A ret = remove(slot);
        add(slot, toEquip);

        return ret;
    }
}
