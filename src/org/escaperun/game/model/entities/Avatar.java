package org.escaperun.game.model.entities;

import com.sun.org.glassfish.external.statistics.Statistic;
import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;

public abstract class Avatar extends Entity {

    protected Avatar(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public Statistic getAvatarStatistics(){
        return null;
    }
}
