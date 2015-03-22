package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.view.Decal;

public class Observe extends Projectile360Effect {
    public Observe(int ofp, int dfp, int skillLevel, Entity skillOwner, int sd, Direction dir, Position start, int movesPerTick) {
        super(ofp, dfp, skillLevel, skillOwner, sd, dir, start, movesPerTick);
    }


    @Override
    public double getGoodStat() {
        return 0;
    }

    @Override
    public SkillSuccess generateSuccess(Entity attacker, Entity defender) {
        return null;
    }
}
