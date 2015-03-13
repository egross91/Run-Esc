package org.escaperun.game.model.items.equipment.weapons.sneak;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Decal;

public abstract class BowWeapon extends WeaponItem {
    public BowWeapon(Decal decal) {
        super(decal);
    }
}
