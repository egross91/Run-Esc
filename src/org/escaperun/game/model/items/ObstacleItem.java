package org.escaperun.game.model.items;

import org.escaperun.game.view.Decal;

import java.awt.*;

public final class ObstacleItem extends Item {

    public ObstacleItem() {
        super(new Decal('B', Color.BLACK, Color.LIGHT_GRAY));
    }

    public ObstacleItem(Decal decal) {
        super(decal);
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public Decal[][] getRenderable() {
        return super.getRenderable();
    }
}
