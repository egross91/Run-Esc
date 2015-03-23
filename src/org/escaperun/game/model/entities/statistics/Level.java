package org.escaperun.game.model.entities.statistics;

public class Level extends DerivedStatistic<Integer> {

    Experience experience;

    public Level(Experience exp) {
        super(0);
        experience = exp;
        experience.subscribe(this);
        recalculate();
    }

    @Override
    protected void recalculate() {
        setBase((int) (experience.getCurrent().doubleValue() / 100.0));
    }

    @Override
    public String getName() {
        return "Level";
    }
}
