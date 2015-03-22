package org.escaperun.game.model.entities.skills.smasher;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.ProjectileQuadrantEffect;
import org.escaperun.game.model.entities.skills.SkillSuccess;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class Cleave extends ProjectileQuadrantEffect{
    public Cleave(int ofp, int dfp, int skillLevel, Entity skillOwner, int sd, Direction dir, Position start, int movesPerTick) {
        super(ofp, dfp, skillLevel, skillOwner, sd, dir, start, movesPerTick);
        this.setDecal(new Decal('~', Color.RED, Color.CYAN));
    }

    @Override
    public SkillSuccess generateSuccess(Entity attacker, Entity defender) {
        return null;
    }
}