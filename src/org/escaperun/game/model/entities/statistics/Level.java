package org.escaperun.game.model.entities.statistics;

public class Level extends DerivedStatistic<Short> {

    Experience experience;
    Short level;


    public Level(Experience exp) {
        experience = exp;
        experience.subscribe(this);
        recalculate();
    }

    @Override
    protected void recalculate() {
        this.level = (short) (experience.getCurrent()/ 100);
    }

    @Override
    public Short getBase() {
        return level;
    }

    @Override
    public Short getCurrent() {
        return level;
    }
}
