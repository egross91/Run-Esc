package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class HardinessBooster extends OneShotItem {
    public HardinessBooster(Decal decal){super(decal);}


    @Override
    public void doAction(Entity e){
        e.getStatContainer().getHardiness().addDelta(1);
    }

}
