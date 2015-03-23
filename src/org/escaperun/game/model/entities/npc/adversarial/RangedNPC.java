package org.escaperun.game.model.entities.npc.adversarial;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.sneak.Arrow;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public class RangedNPC extends AdversarialNPC{
    public RangedNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius, 20);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().setBase(1);
        stats.getMovement().setBase(700);
        stats.setWeaponDamage(25.0);
        setStatContainer(stats);
    }

    @Override
    public ActiveSkill getActiveSkill(Direction dir) {
        return new Arrow(100, 0, 0, this, getMaxAttackRange(), dir, getCurrentPosition(),10);
    }
}
