package org.escaperun.game.model.items.equipment.weapons.smasher;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponVisitor;
import org.escaperun.game.model.items.equipment.weapons.MeleeWeapon;
import org.escaperun.game.view.Decal;

public class TwoHandedWeapon extends MeleeWeapon {
    public TwoHandedWeapon(Decal decal) {
        super(decal);
    }

    @Override
    protected EquipableItem equipItem(EquipmentContainer<EquipableItem> equipment, EquipableItem item) {
        return null;
    }

    @Override
    public void doAction(Entity e){

    }

    @Override
    public void accept(WeaponVisitor weaponVisitor) {
        weaponVisitor.visit(this);
    }
}
