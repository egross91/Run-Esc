package org.escaperun.game.model.tile;

import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WaterTerrain extends Terrain {

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

    @Override
    public Decal[][] getRenderable() {
        return new Decal[0][];
    }
}
