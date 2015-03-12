package org.escaperun.game.model.items.equipment.weapons;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.view.Decal;

public abstract class MeleeItem extends WeaponItem {
    public MeleeItem(Decal decal) {
        super(decal);
    }

    @Override
    public abstract void doAction(Entity e);
}
