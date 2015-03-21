package org.escaperun.game.model.items.equipment.weapons.sneak;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponVisitor;
import org.escaperun.game.model.items.equipment.weapons.RangedWeapon;
import org.escaperun.game.view.Decal;

public class ThrowingKnivesWeapon extends RangedWeapon {
    public ThrowingKnivesWeapon(Decal decal) {
        super(decal);
    }

    @Override
    public void doAction(Entity e) {

    }

    @Override
    protected EquipableItem equipItem(EquipmentContainer<EquipableItem> equipment, EquipableItem item) {
        return null;
    }

    @Override
    public void accept(WeaponVisitor weaponVisitor) {
        weaponVisitor.visit(this);
    }
}
