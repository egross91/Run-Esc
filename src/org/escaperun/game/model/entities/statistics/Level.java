package org.escaperun.game.model.entities.statistics;

public class Level extends DerivedStatistic {

    Experience experience;
    Double level;


    public Level(Experience exp) {
        experience = exp;
        experience.subscribe(this);
        recalculate();
    }

    @Override
    protected void recalculate() {
        this.level = (double) (experience.getCurrent()/ 100);
    }

    @Override
    public Double getBase() {
        return level;
    }

    @Override
    public Double getCurrent() {
        return level;
    }
}
