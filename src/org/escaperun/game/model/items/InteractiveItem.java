package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

/**
 * REMARKS
 * This class should be extended for Items such as Quest or Puzzle Items.
 */
public abstract class InteractiveItem extends TouchableItem {

    public InteractiveItem(Decal decal, String name, String description) {
        super(decal, name, description);
    }

    @Override
    public abstract boolean isCollidable();

    @Override
    public abstract void onTouch(Entity e);

    @Override
    public abstract void doAction(Entity e);

    @Override
    public Decal[][] getRenderable() {
        return super.getRenderable();
    }
}
