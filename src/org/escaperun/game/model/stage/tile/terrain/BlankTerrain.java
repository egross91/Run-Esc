package org.escaperun.game.model.stage.tile.terrain;

import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class BlankTerrain extends Terrain {

    public BlankTerrain() {
        super(Decal.BLANK);
    }

    @Override
    public Decal[][] getRenderable() {
        return super.getRenderable();
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element terrain = super.save(dom, parent);
        terrain.setAttribute("Type", "Blank");
        return terrain;
    }
}
