package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public abstract class MeleeItem extends EquipableItem {
    public MeleeItem(Decal decal, EquipmentSlot slot) {
        super(decal, slot);
    }

    @Override
    public abstract void doAction(Entity e);
}
