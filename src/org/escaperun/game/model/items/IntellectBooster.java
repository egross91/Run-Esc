package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class IntellectBooster extends OneShotItem {
    public IntellectBooster(Decal decal){super(decal);}

    @Override
    public void doAction(Entity e){
        Integer amount = e.getStatContainer().getIntellect().getBase() + 2;
        e.getStatContainer().getIntellect().setBase(amount);
    }
}
