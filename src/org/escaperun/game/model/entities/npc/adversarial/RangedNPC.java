package org.escaperun.game.model.entities.npc.adversarial;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.RangedAttack;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public class RangedNPC extends AdversarialNPC{
    public RangedNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().setBase(1);
        stats.getMovement().setBase(700);
        setStatContainer(stats);
        setSkill(new RangedAttack());
    }
}
