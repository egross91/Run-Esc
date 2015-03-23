package org.escaperun.game.model.items.equipment.armors;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.view.Decal;

public class ChestItem extends EquipableItem {

    public ChestItem(Decal decal) {
        super(decal, EquipmentSlot.BODY, "Chest Armor", "Protect mah man boobies");
    }

    @Override
    protected EquipableItem equipItem(EquipmentContainer<EquipableItem> equipment, EquipableItem item) {
        return equipment.equipItem(item);
    }

    @Override
    public String getName() {
        return "Chest Armor";
    }

    @Override
    public String getDescription() {
        return "Protect mah man boobies";
    }

    @Override
    public void doAction(Entity e) {

    }
}
