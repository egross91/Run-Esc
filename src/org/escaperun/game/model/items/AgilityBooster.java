package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class AgilityBooster extends OneShotItem {
    public AgilityBooster(Decal decal){super(decal);}

    @Override
    public void doAction(Entity e){
        e.getStatContainer().getAgility().addDelta(1.0);
    }
}
