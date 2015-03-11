package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.EquipableItem;
import org.escaperun.game.model.items.MeleeItem;
import org.escaperun.game.view.Decal;

public class Avatar extends Entity {
    private EquipmentContainer<EquipableItem> equipment;

    public Avatar(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
        this.equipment = new EquipmentContainer<EquipableItem>();
    }

    public Avatar(Decal decal, Position initialPosition, EquipmentContainer<EquipableItem> equipment) {
        super(decal, initialPosition);
        this.equipment = equipment;
    }

    @Override
    public void equipItem(EquipableItem item) {
        equipment.equipItem(item);
    }

    @Override
    public void move(Position p) {
        setCurrentPosition(p);
    }
}
