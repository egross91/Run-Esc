package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public abstract class StatisticAlteringAreaEffect extends AreaEffect {

    public StatisticAlteringAreaEffect(Decal decal, Position position) {
        super(decal, position);
    }

    @Override
    public void onTouch(Entity e) {
        doAction(e);
    }
}
