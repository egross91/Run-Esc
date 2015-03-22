package org.escaperun.game.model.items.equipment;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.view.Decal;

public abstract class EquipableItem extends TakeableItem implements Equipable {
    private EquipmentSlot equipmentSlot;

    public enum EquipmentSlot {
        HEAD,
        BODY,
        SHIELD,
        FEET,
        RING,
        WEAPON;

        public int getSlot() {
            return ordinal();
        }
    }

    public EquipableItem(Decal decal, EquipmentSlot slot) {
        super(decal);
        this.equipmentSlot = slot;
    }

    public int getEquipmentSlot() {
        return this.equipmentSlot.getSlot();
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

    protected abstract EquipableItem equipItem(EquipmentContainer<EquipableItem> equipment, EquipableItem item);

    public abstract Double getValue();
}
