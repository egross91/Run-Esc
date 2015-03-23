package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class OneUp extends OneShotItem {

    public OneUp(Decal decal){super(decal);}

    @Override
    public void doAction(Entity e){
        Integer amount = e.getStatContainer().getLivesLeft().getBase() + 1;
        e.getStatContainer().getLivesLeft().setBase(amount);
    }

}
