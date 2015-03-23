package org.escaperun.game.model.entities.skills.sneak;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.skills.LinearSkillSuccess;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.statistics.DefensiveRating;
import org.escaperun.game.model.entities.statistics.Intellect;
import org.escaperun.game.view.Decal;

import java.awt.*;

/**
 * Created by agemery on 3/22/15.
 */
public class Arrow extends Projectile {

    //this
    public Arrow(int ofp, int dfp, int skillLevel, Entity skillOwner, int sd, Direction dir, Position start, int movesPerTick){
        super(ofp,dfp,skillLevel,skillOwner,sd,dir,start, movesPerTick);
        this.setDecal(new Decal('>', Color.black, Color.red));
    }

    @Override
    public double generateSuccess(Entity attacker, Entity defender) {
        return 0;
    }

    @Override
    public String getName() {
        return "Arrow";
    }

    public double getGoodStat(){
        return this.getOwner().getStatContainer().getAgility().getCurrent();
    }
}
