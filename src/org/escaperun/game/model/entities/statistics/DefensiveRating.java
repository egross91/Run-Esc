package org.escaperun.game.model.entities.statistics;

public class DefensiveRating extends DerivedStatistic<Double> {

    Agility agile;
    Level lvl;

    public DefensiveRating(Agility agility, Level level) {
        super(0.0); // dummy
        agile = agility;
        agile.subscribe(this);
        lvl = level;
        lvl.subscribe(this);
        recalculate();
    }

    @Override
    protected void recalculate() {
        setBase((double) (3*agile.getCurrent() + 2*lvl.getCurrent()));
    }

    @Override
    public String getName() {
        return "Def. Rating";
    }
}
