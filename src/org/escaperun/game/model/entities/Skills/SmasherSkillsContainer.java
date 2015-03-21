package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class SmasherSkillsContainer extends SkillsContainer {

    OneHandedWeapon ohw;
    TwoHandedWeapon thw;
    FistWeapon fist;

    public SmasherSkillsContainer(){
        super();
        ohw = new OneHandedWeapon(new Decal('t', Color.BLACK, Color.BLUE));
        thw = new TwoHandedWeapon(new Decal('T', Color.BLACK, Color.BLUE));
        fist = new FistWeapon(new Decal('F', Color.BLACK, Color.BLUE));
    }

    public OneHandedWeapon getOneHandedWeapon(){
        return ohw;
    }

    public TwoHandedWeapon getTwoHandedWeapon(){
        return thw;
    }

    public FistWeapon getFistWeapon(){
        return fist;
    }
}
