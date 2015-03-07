package org.escaperun.game.model.items;

import org.escaperun.game.model.Collidable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public abstract class Item implements Renderable, Collidable {
    private final Decal decal;

    public Item(Decal decal) {
        this.decal = decal;
    }

    /**
     * INTERFACE IMPLEMENTATIONS
     */
    @Override
    public abstract boolean isCollidable();

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ this.decal }};
    }
}
