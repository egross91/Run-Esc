package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public abstract class RangeItem extends EquipableItem {
    public RangeItem(Decal decal, EquipmentSlot slot) {
        super(decal, slot);
    }

    public abstract void doAction(Entity e);
}
