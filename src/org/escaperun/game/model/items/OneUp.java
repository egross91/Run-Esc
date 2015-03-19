package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class OneUp extends OneShotItem {

    public OneUp(Decal decal){super(decal);}

    @Override
    public void doAction(Entity e){
        //TODO: gain one more life
    }

}
