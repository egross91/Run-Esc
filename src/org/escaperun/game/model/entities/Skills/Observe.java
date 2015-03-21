package org.escaperun.game.model.entities.Skills;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;

public class Observe extends Projectile360Effect {
    public Observe(int ofp, int dfp, int sd, Direction dir, Position start, Decal dec) {
        super(ofp, dfp, sd, dir, start, dec);
    }

    @Override
    public String getBeneficialStat() {
        return null;
    }
}
