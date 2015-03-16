package org.escaperun.game.model.items.equipment.weapons.smasher;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponVisitor;
import org.escaperun.game.model.items.equipment.weapons.MeleeWeapon;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Decal;

public class FistWeapon extends MeleeWeapon {
    public FistWeapon(Decal decal) {
        super(decal);
    }

    @Override
    public void doAction(Entity e) {

    }

    @Override
    protected EquipableItem equipItem(EquipmentContainer<ArmorItem, ? extends WeaponItem> equipment, EquipableItem item) {
        return null;
    }

    @Override
    public void accept(WeaponVisitor weaponVisitor) {
        weaponVisitor.visit(this);
    }
}
