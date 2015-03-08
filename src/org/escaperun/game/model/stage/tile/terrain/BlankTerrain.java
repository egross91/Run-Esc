package org.escaperun.game.model.stage.tile.terrain;

import org.escaperun.game.view.Decal;

import java.awt.*;

public class BlankTerrain extends Terrain {

    public BlankTerrain() {
        super(Decal.BLANK);
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
