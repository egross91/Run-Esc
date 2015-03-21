package org.escaperun.game.model.entities.statistics;

public class OffensiveRating extends DerivedStatistic<Integer>{

    Strength str;
    Level lvl;
    int weaponDamage;
    int offensiveRating;

    //need a way to alert when weapon changes
    public OffensiveRating(Strength strength, Level level, int equippedWeaponDamage) {
        str = strength;
        lvl = level;
        weaponDamage = equippedWeaponDamage;
        recalculate();
    }

    @Override
    protected void recalculate() {
        //idk I made something up
        //also need a way to notify when weapon changes
        this.offensiveRating = (str.getCurrent() * lvl.getCurrent()) + weaponDamage;
    }


    @Override
    public Integer getBase() {
        return offensiveRating;
    }

    @Override
    public Integer getCurrent() {
        return offensiveRating;
    }
}
