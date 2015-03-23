package org.escaperun.game.model.items.equipment;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.options.ItemOption;
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

    public EquipableItem(Decal decal, EquipmentSlot slot, String name, String description) {
        super(decal, name, description);
        this.equipmentSlot = slot;
    }

    public EquipableItem(Decal decal, EquipmentSlot slot, StatisticContainer stats, String name, String description) {
        super(decal, stats, name, description);
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

    @Override
    public ItemOption[] getOptions(final Avatar avatar) {
        final EquipableItem self = this;

        ItemOption equip = new ItemOption("Equip: ", new Runnable() {
            @Override
            public void run() {
                if (avatar.getInventory().contains(self)) {
                    avatar.equipItem(self);
                    avatar.getInventory().remove(self);
                }
            }
        });
        ItemOption unequip = new ItemOption("Unequip: ", new Runnable() {
            @Override
            public void run() {
                if (avatar.getEquipment().contains(self)) {
                    avatar.unequipItem(self);
                }
            }
        });
        ItemOption drop = new ItemOption("Drop; ", new Runnable() {
            @Override
            public void run() {
                if (avatar.getEquipment().contains(self)) {
                    avatar.unequipItem(self);
                }

                avatar.getInventory().remove(self);
            }
        });

        return new ItemOption[] { equip, unequip, drop, ItemOption.NEVERMIND };
    }
}
