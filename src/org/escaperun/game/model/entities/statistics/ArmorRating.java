package org.escaperun.game.model.entities.statistics;

public class ArmorRating extends DerivedStatistic<Double>{
    //this class is probably analagous to Life

    Hardiness hard;
    Double armorValue;
    Double armorRating;

    //TODO
    //need a way to alert when armor changes
    public ArmorRating(Hardiness hardiness, Double armorValue) {
        hard = hardiness;
        this.armorValue = armorValue;
        recalculate();
    }

    @Override
    protected void recalculate() {
        armorRating = (2 * hard.getCurrent()) + (3 * armorValue);
    }

    public Double getBase() {
        return armorRating;
    }

    @Override
    public Double getCurrent() {
        return armorRating;
    }

    public void setArmorValue(Double armorValue) {
        this.armorValue = armorValue;
        recalculate();
    }
}
