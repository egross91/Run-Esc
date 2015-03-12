package org.escaperun.game.model.items.equipment.weapons;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.view.Decal;

public abstract class WeaponItem extends EquipableItem {
    public WeaponItem(Decal decal) {
        super(decal);
    }

    @Override
    public EquipableItem equip(Entity e) {
        return equipItem(e.getEquipment(), this);
    }

    @Override
    protected EquipableItem equipItem(EquipmentContainer<ArmorItem, WeaponItem> equipment, EquipableItem item) {
        return equipment.equipWeapon((WeaponItem)item);
    }
}

