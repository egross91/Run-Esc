package org.escaperun.game.model.entities.handlers;

import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.events.Timer;

/**
 * Created by agemery on 3/22/15.
 */
public class RestorationHandler implements Tickable {

    private Entity entity;
    private Timer restoreTimer;

    public RestorationHandler(Entity entity, int ticksPerRestore) {
        this.entity = entity;
        this.restoreTimer = new Timer(ticksPerRestore);
    }

    public void tick() {
        if (entity.getStatContainer().getIntellect().getCurrent() * restoreTimer.getTicksSince() >=
                restoreTimer.getTicksTo()) {
            entity.getStatContainer().getMana().restoreMana(1);
            restoreTimer.reset();
        }
        restoreTimer.tick();
    }
}
