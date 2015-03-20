package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

public class Smasher extends Avatar{
    public Smasher(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public Smasher(Decal decal, Position initialPosition, EquipmentContainer<ArmorItem, WeaponItem> equipmentContainer, ItemContainer<TakeableItem> itemContainer) {
        super(decal, initialPosition, equipmentContainer, itemContainer);
    }

    @Override
    public void visit(TwoHandedWeapon thw) {
        thw.accept(this);
    }

    @Override
    public void visit(OneHandedWeapon ohw) {
        ohw.accept(this);
    }

    @Override
    public void visit(FistWeapon fw) {
        fw.accept(this);
    }

    @Override
    public void visit(BoomstickWeapon bsw) {
        bsw.accept(this);
    }

    @Override
    public void visit(BowWeapon bw) {
        bw.accept(this);
    }

    @Override
    public void visit(ThrowingKnivesWeapon tkw) {
        tkw.accept(this);
    }

    @Override
    public void visit(StaffWeapon sw) {
        sw.accept(this);
    }

}
