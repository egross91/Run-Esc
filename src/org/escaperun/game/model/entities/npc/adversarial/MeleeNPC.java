package org.escaperun.game.model.entities.npc.adversarial;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public class MeleeNPC extends AdversarialNPC {

    public MeleeNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().setBase(1);
        stats.getHardiness().setBase(10);
        stats.getExperience().setBase(1000);
        stats.getMovement().setBase(1000);
        setStatContainer(stats);
    }
}
