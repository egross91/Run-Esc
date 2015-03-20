package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class Telescope extends InteractiveItem {
    public Telescope(Decal decal){super(decal);}

    @Override
    public boolean isCollidable(){return true;}

    @Override
    public void onTouch(Entity e){}

    @Override
    public void doAction(Entity e){}


}
