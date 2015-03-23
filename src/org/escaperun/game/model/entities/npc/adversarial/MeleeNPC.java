package org.escaperun.game.model.entities.npc.adversarial;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.OneHandedAttack;
import org.escaperun.game.model.entities.skills.smasher.Cleave;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public class MeleeNPC extends AdversarialNPC {

    public MeleeNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius, 1);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().setBase(1);
        stats.getMovement().setBase(500);
        stats.setWeaponDamage(100.0);
        setStatContainer(stats);
    }

    @Override
    public ActiveSkill getActiveSkill(Direction dir) {
        double temp = getStatContainer().getOffensiveRating().getCurrent();
        return new Cleave((int)temp, 0, 0, this, getMaxAttackRange() + 1, dir, getCurrentPosition(), 2);
    }
}
