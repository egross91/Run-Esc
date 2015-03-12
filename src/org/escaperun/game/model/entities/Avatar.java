package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Decal;

public class Avatar extends Entity {

    public Avatar(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public Avatar(Decal decal, Position initialPosition, EquipmentContainer<ArmorItem, WeaponItem> equipment) {
        super(decal, initialPosition, equipment);
    }

    @Override
    public void equipItem(EquipableItem item) {
        item.doAction(this);
    }

    @Override
    public void move(Position p) {
        setCurrentPosition(p);
    }
}
