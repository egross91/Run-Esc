package org.escaperun.game.model.items.equipment.visitors;

import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;

public interface WeaponVisitor {
    public void visit(TwoHandedWeapon thw);
    public void visit(OneHandedWeapon ohw);
    public void visit(FistWeapon fw);
    public void visit(BoomstickWeapon bsw);
    public void visit(BowWeapon bw);
    public void visit(ThrowingKnivesWeapon tkw);
    public void visit(StaffWeapon sw);
}
