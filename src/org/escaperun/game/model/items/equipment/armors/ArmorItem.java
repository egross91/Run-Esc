package org.escaperun.game.model.items.equipment.armors;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.Equipable;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Decal;

public abstract class ArmorItem extends EquipableItem {
    private EquipmentSlot equipmentSlot;

    public enum EquipmentSlot {
        HEAD,
        BODY,
        SHIELD,
        FEET,
        RING;

        public int getSlot() {
            return ordinal();
        }
    }

    public ArmorItem(Decal decal, EquipmentSlot slot) {
        super(decal);
        this.equipmentSlot = slot;
    }

    public EquipmentSlot getEquipmentSlot() {
        return this.equipmentSlot;
    }

    @Override
    public EquipableItem equip(Entity e) {
        return equipItem(e.getEquipment(), this);
    }

    @Override
    protected ArmorItem equipItem(EquipmentContainer<ArmorItem, ? extends WeaponItem> equipment, EquipableItem item) {
        return equipment.equipArmor((ArmorItem)item);
    }
}
