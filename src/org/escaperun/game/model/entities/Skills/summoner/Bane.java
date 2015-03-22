package org.escaperun.game.model.entities.skills.summoner;

import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.skills.Projectile360Effect;
import org.escaperun.game.model.entities.skills.ProjectileQuadrantEffect;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class Bane extends Projectile360Effect {

    public Bane(int ofp, int dfp, int sd, Direction dir, Position start){
        super(ofp,dfp,sd,dir,start);
        this.setDecal(new Decal('*', Color.black, Color.red));
    }

    public String getBeneficialStat() {
        return null;
    }
}
