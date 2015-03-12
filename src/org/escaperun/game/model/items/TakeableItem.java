package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public abstract class TakeableItem extends TouchableItem {

    public TakeableItem(Decal decal) {
        super(decal);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    /**
     * REMARKS
     * Implement the logic for doAction() in the subclasses.
     */
    @Override
    public void onTouch(Entity e) {
        e.takeItem(this);
    }
}
