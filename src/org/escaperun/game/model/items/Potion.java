package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;


public class Potion extends TakeableItem {
    public Potion(Decal decal){super(decal);}

    @Override
    public void doAction(Entity e){
        //TODO: implement some kind of effects when entity drink potion
    }
}
