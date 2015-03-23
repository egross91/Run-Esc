package org.escaperun.game.model.items.equipment.weapons;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponElement;
import org.escaperun.game.view.Decal;

public abstract class WeaponItem extends EquipableItem implements WeaponElement {

    public WeaponItem(Decal decal, String name, String description) {
        super(decal, EquipmentSlot.WEAPON, name, description);
    }

    @Override
    public EquipableItem equip(Entity e) {
        return equipItem(e.getEquipment(), this);
    }
}

