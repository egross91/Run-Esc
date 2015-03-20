package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.MagicalWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;

public class Summoner extends Avatar{
    public Summoner(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public Summoner(Decal decal, Position initialPosition, EquipmentContainer<ArmorItem, MagicalWeapon> magicalEquipmentContainer, ItemContainer<TakeableItem> itemContainer) {
        super(decal, initialPosition, magicalEquipmentContainer, itemContainer);
    }

    @Override
    public void visit(TwoHandedWeapon thw) {
        //TODO: Implement message (when we have a Game Screen) stating that this class cannot use this type of weapon.
    }

    @Override
    public void visit(OneHandedWeapon ohw) {
        //TODO: Implement message (when we have a Game Screen) stating that this class cannot use this type of weapon.
    }

    @Override
    public void visit(FistWeapon fw) {
        //TODO: Implement message (when we have a Game Screen) stating that this class cannot use this type of weapon.
    }

    @Override
    public void visit(BoomstickWeapon bsw) {
        //TODO: Implement message (when we have a Game Screen) stating that this class cannot use this type of weapon.
    }

    @Override
    public void visit(BowWeapon bw) {
        //TODO: Implement message (when we have a Game Screen) stating that this class cannot use this type of weapon.
    }

    @Override
    public void visit(ThrowingKnivesWeapon tkw) {
        //TODO: Implement message (when we have a Game Screen) stating that this class cannot use this type of weapon.
    }

    @Override
    public void visit(StaffWeapon sw) {
        sw.accept(this);
    }
}
