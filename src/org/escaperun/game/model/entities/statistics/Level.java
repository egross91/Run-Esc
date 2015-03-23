package org.escaperun.game.model.entities.statistics;

public class Level extends DerivedStatistic<Integer> {

    Experience experience;
    Integer divisor = 100;

    public Level(Experience exp) {
        super(0);
        experience = exp;
        experience.subscribe(this);
        recalculate();
    }

    @Override
    protected void recalculate() {
        setBase(experience.getCurrent().intValue() / divisor));

    }

    @Override
    public String getName() {
        return "Level";
    }

    //get remaining experience until next level
    public Integer getRemainingExp() {
        return (divisor - (experience.getCurrent() % divisor));
    }
}
