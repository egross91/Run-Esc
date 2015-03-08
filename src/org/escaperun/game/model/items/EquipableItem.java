package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class EquipableItem extends TakeableItem {
    private EquipmentSlot equipmentSlot;

    public enum EquipmentSlot {
        HEAD(0),
        BODY(1),
        SHIELD(2),
        WEAPON(3),
        FEET(4);

        private int slot;

        EquipmentSlot(int slot) {
            this.slot = slot;
        }

        private int getSlot() {
            return this.slot;
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
