package org.escaperun.game.model.items.equipment;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Decal;

public abstract class EquipableItem extends TakeableItem implements Equipable {
    public EquipableItem(Decal decal) {
        super(decal);
    }

    @Override
    public boolean isCollidable() {
        return super.isCollidable();
    }

    @Override
    public void onTouch(Entity e) {
        super.onTouch(e);
    }

    @Override
    public EquipableItem equip(Entity e) {
        return equipItem(e.getEquipment(), this);
    }

    protected abstract EquipableItem equipItem(EquipmentContainer<ArmorItem, WeaponItem> equipment, EquipableItem item);
}
