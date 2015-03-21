package org.escaperun.game.model.entities.statistics;

public class DefensiveRating extends DerivedStatistic<Integer>{

    Agility agile;
    Level lvl;
    int defensiveRating;

    public DefensiveRating(Agility agility, Level level) {
        agile = agility;
        lvl = level;
        recalculate();
    }

    @Override
    protected void recalculate() {
        defensiveRating = (3*agile.getCurrent() + 2*lvl.getCurrent());
    }

    @Override
    public Integer getBase() {
        return defensiveRating;
    }

    @Override
    public Integer getCurrent() {
        return defensiveRating;
    }
}
