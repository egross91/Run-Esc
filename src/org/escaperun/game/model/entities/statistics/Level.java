package org.escaperun.game.model.entities.statistics;

public class Level extends DerivedStatistic<Integer> {

    Experience experience;
    Integer level = 0;


    public Level(Experience exp) {
        super(0);
        experience = exp;
        experience.subscribe(this);
        recalculate();
    }

    @Override
    protected void recalculate() {
        int val = (int) (experience.getCurrent().doubleValue() / 100.0);
    }

    @Override
    public Integer getBase() {
        return level;
    }

    @Override
    public Integer getCurrent() {
        return level;
    }

    @Override
    public String getName() {
        return "Level";
    }
}
