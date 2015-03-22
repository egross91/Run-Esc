package org.escaperun.game.model.entities.npc.adversarial;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public class MeleeNPC extends AdversarialNPC {

    public MeleeNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().addDelta(1);
        stats.getHardiness().addDelta(10);
        stats.getExperience().addDelta(1000);
        stats.getMovement().addDelta(100);
        setStatContainer(stats);
    }
}
