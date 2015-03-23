package org.escaperun.game.model.entities.statistics;

public class ArmorRating extends DerivedStatistic<Double> {
    //this class is probably analagous to Life

    Hardiness hard;
    Double armorValue;


    //this statistic should be alerted when armor changes
    //that mechanism should be working..
    public ArmorRating(Hardiness hardiness, Double armorValue) {
        super(0.0); // dummy
        hard = hardiness;
        hardiness.subscribe(this);
        this.armorValue = armorValue;
        recalculate();
    }

    @Override
    protected void recalculate() {
        setBase((Double) (2 * hard.getCurrent() + (3 * armorValue)));
    }

    protected void setArmorValue(Double armorValue) {
        this.armorValue = armorValue;
        recalculate();
    }

    @Override
    public String getName() {
        return "ArmorRating";
    }
}
