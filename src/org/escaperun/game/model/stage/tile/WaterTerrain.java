package org.escaperun.game.model.stage.tile;

import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class WaterTerrain extends Terrain {

    public WaterTerrain(){
        super(new Decal((char) 247, Color.BLACK, new Color(0, 200, 255)));
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

    @Override
    public Terrain load(Element node) {
        return super.load(node);
    }
}
