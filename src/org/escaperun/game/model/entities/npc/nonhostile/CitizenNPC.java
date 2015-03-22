package org.escaperun.game.model.entities.npc.nonhostile;


import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public class CitizenNPC extends NonHostileNPC {
    public CitizenNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().setBase(1);
        stats.getHardiness().setBase(1);
        stats.getExperience().setBase(0);
        stats.getMovement().setBase(50);
        setStatContainer(stats);
    }
}
