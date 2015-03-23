package org.escaperun.game.model.items.equipment.armors;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.view.Decal;

public class ChestItem extends EquipableItem {

    public ChestItem(Decal decal, String name, String desc) {
        super(decal, EquipmentSlot.BODY, name, desc);
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
