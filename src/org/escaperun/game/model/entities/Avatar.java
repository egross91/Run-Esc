package org.escaperun.game.model.entities;

import com.sun.org.glassfish.external.statistics.Statistic;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Decal;

public abstract class Avatar extends Entity {

    protected Avatar(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public Avatar(Decal decal, Position initialPosition, EquipmentContainer<ArmorItem, ? extends WeaponItem> equipment) {
        super(decal, initialPosition, equipment);
    }

    public Avatar(Decal decal, Position initialPosition, EquipmentContainer<ArmorItem, ? extends WeaponItem> equipment, ItemContainer<TakeableItem> inventory) {
        super(decal, initialPosition, equipment, inventory);
    }

    @Override
    public void equipItem(EquipableItem item) {
        item.equip(this);
    }

    public Statistic getAvatarStatistics(){
        return null;
    }
}
