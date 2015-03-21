package org.escaperun.game.model.entities.statistics;

public class ArmorRating extends DerivedStatistic<Integer>{
    //this class is probably analagous to Life

    Hardiness hard;
    int armorValue;
    int armorRating;

    //TODO
    //need a way to alert when armor changes
    public ArmorRating(Hardiness hardiness, int armorValue) {
        hard = hardiness;
        this.armorValue = armorValue;
        recalculate();
    }

    @Override
    protected void recalculate() {
        armorRating = (2 * hard.getCurrent()) + (3 * armorValue);
    }

    public Integer getBase() {
        return armorRating;
    }

    @Override
    public Integer getCurrent() {
        return armorRating;
    }

    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
        recalculate();
    }
}
