package org.escaperun.game.model.items.equipment.weapons;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public abstract class RangeItem extends WeaponItem {
    public RangeItem(Decal decal) {
        super(decal);
    }

    public abstract void doAction(Entity e);
}
