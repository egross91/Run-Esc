package org.escaperun.game.model.entities.statistics;

public class OffensiveRating extends DerivedStatistic<Double> {

    Strength str;
    Level lvl;
    Double weaponDamage;
    Double offensiveRating;

    //TODO
    //need a way to alert when weapon changes
    public OffensiveRating(Strength strength, Level level, Double equippedWeaponDamage) {
        super(0.0);
        str = strength;
        lvl = level;
        weaponDamage = equippedWeaponDamage;
        recalculate();
    }

    @Override
    protected void recalculate() {
        //idk I made something up
        //also need a way to notify when weapon changes
        offensiveRating = (double) (3* str.getCurrent().doubleValue() + 2*lvl.getCurrent().doubleValue()) + weaponDamage;
    }


    @Override
    public Double getBase() {
        return offensiveRating;
    }

    @Override
    public Double getCurrent() {
        return offensiveRating;
    }

    protected void setWeaponDamage(Double weaponDamage) {
        this.weaponDamage = weaponDamage;
        recalculate();
    }
}
