package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public abstract class StatisticAlteringAreaEffect extends AreaEffect {
    private StatisticContainer statisticAlterations;

    public StatisticAlteringAreaEffect(Decal decal, StatisticContainer stats) {
        super(decal);
        this.statisticAlterations = stats;
    }

    @Override
    public void onTouch(Entity e) {
        doAction(e);
    }
}
