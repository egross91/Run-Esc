package org.escaperun.game.model.items.equipment.weapons.summoner;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponVisitor;
import org.escaperun.game.model.items.equipment.weapons.MagicalWeapon;
import org.escaperun.game.view.Decal;

public class StaffWeapon extends MagicalWeapon {
    public StaffWeapon(Decal decal, String name, String description) {
        super(decal);
        this.name = name;
        this.description = description;
    }
    private final String name;
    private final String description;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
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
