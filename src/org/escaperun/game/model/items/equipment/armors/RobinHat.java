package org.escaperun.game.model.items.equipment.armors;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.view.Decal;

public class RobinHat extends EquipableItem {

    public RobinHat(Decal decal) {
        super(decal, EquipmentSlot.HEAD, "Robin Hood Hat", "YOURE SO GREAT");
    }

    @Override
    protected EquipableItem equipItem(EquipmentContainer<EquipableItem> equipment, EquipableItem item) {
        return equipment.equipItem(item);
    }

    @Override
    public String getName() {
        return "Robin Hood Hat";
    }

    @Override
    public String getDescription() {
        return "YOURE SO GREAT";
    }

    @Override
    public void doAction(Entity e) {

    }
}
