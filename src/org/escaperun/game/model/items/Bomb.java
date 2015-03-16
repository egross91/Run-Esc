package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class Bomb extends TakeableItem{
    public Bomb(Decal decal){super(decal);}

    @Override
    public void doAction(Entity e){
        //TODO: implement some effect when entity deploy bomb
    }

}
