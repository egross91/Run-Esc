package org.escaperun.game.model.items;

import org.escaperun.game.model.Actionable;
import org.escaperun.game.model.Touchable;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public abstract class TouchableItem extends Item implements Touchable, Actionable {

    public TouchableItem(Decal decal) {
        super(decal);
    }

    @Override
    public abstract boolean isCollidable();

    @Override
    public abstract void onTouch(Entity e);

    @Override
    public abstract void doAction(Entity e);
}
