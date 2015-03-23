package org.escaperun.game.model.entities.statistics;

public class OffensiveRating extends DerivedStatistic<Double> {

    Strength str;
    Level lvl;
    Double weaponDamage;

    //TODO
    //need a way to alert when weapon changes
    public OffensiveRating(Strength strength, Level level, Double equippedWeaponDamage) {
        super(0.0);
        str = strength;
        str.subscribe(this);
        lvl = level;
        lvl.subscribe(this);
        weaponDamage = equippedWeaponDamage;
        recalculate();
    }

    @Override
    protected void recalculate() {
        //idk I made something up
        //also need a way to notify when weapon changes
        setBase((3* str.getCurrent().doubleValue() + 2*lvl.getCurrent().doubleValue()) + weaponDamage);
    }

    protected void setWeaponDamage(Double weaponDamage) {
        this.weaponDamage = weaponDamage;
        recalculate();
    }

    @Override
    public String getName() {
        return "Off. Rating";
    }
}
