package org.escaperun.game.model.entities.statistics;

public class DefensiveRating extends DerivedStatistic{

    Agility agile;
    Level lvl;
    Double defensiveRating;

    public DefensiveRating(Agility agility, Level level) {
        agile = agility;
        lvl = level;
        recalculate();
    }

    @Override
    protected void recalculate() {
        defensiveRating = (double) (3*agile.getCurrent() + 2*lvl.getCurrent());
    }

    @Override
    public Double getBase() {
        return defensiveRating;
    }

    @Override
    public Double getCurrent() {
        return defensiveRating;
    }
}
