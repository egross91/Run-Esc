package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public abstract class OneShotItem extends TouchableItem {

    public OneShotItem(Decal decal) {
        super(decal);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    /**
     * REMARKS
     * Implement the effects that will take place in the subclasses for this Item.
     */
    @Override
    public void onTouch(Entity e) {
        doAction(e);
    }

    @Override
    public abstract void doAction(Entity e);
}
