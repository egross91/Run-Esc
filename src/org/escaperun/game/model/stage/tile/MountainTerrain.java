package org.escaperun.game.model.stage.tile;

import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class MountainTerrain extends Terrain {

    public MountainTerrain(){
        super(new Decal((char) 30, Color.BLACK, Color.LIGHT_GRAY));
    }

    /**
     * INTERFACE IMPLEMENTATIONS
     */
    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public Decal[][] getRenderable() {
        return super.getRenderable();
    }

    @Override
    public Element save(Document dom) {
        Element terrainElement = super.save(dom);
        terrainElement.setAttribute("type", getClass().toString());

        return terrainElement;
    }
}
