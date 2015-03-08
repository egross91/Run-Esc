package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class EquipableItem extends TakeableItem {
    private EquipmentSlot equipmentSlot;

    public enum EquipmentSlot {
        HEAD,
        BODY,
        SHIELD,
        WEAPON,
        FEET;

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
    public void doAction(Entity e) {
        e.equipItem(this);
    }
}
