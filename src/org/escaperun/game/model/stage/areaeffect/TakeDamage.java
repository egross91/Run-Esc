package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public class TakeDamage extends StatisticAlteringAreaEffect {
    public TakeDamage(Decal decal, Position position, StatisticContainer stats){
        super(decal, position, stats);
    }

    @Override
    public void doAction(Entity e){
        e.getStatContainer().getLife().takeDamage(20);
    }


}
