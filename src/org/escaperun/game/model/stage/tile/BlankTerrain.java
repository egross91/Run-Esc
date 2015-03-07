package org.escaperun.game.model.stage.tile;

import org.escaperun.game.view.Decal;

import java.awt.*;

public class BlankTerrain extends Terrain {

    public BlankTerrain() {
        super(new Decal('\u0000', Color.BLACK, Color.BLACK));
    }

    /**
     * INTERFACE IMPLEMENTATIONS
     */
    @Override
    public Decal[][] getRenderable() {
        return super.getRenderable();
    }

    @Override
    public boolean isCollidable() {
        return true;
    }
}
