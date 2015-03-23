package org.escaperun.game.model.items.equipment.armors;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.view.Decal;

public class RobeBottom extends EquipableItem {

    public RobeBottom(Decal decal) {
        super(decal, EquipmentSlot.FEET, "Robe Bottom!!!", "You're a wizard, Harry.");
    }

    @Override
    protected EquipableItem equipItem(EquipmentContainer<EquipableItem> equipment, EquipableItem item) {
        return equipment.equipItem(item);
    }

    @Override
    public String getName() {
        return "Robe Bottom!!!";
    }

    @Override
    public String getDescription() {
        return "You're a wizard, Harry.";
    }

    @Override
    public void doAction(Entity e) {

    }
}
