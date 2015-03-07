package org.escaperun.game.model.tile;

import org.escaperun.game.serialization.Savable;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Terrain implements Renderable, Savable {


    @Override
    public Element save(Document dom) {
        Element terrainElement = dom.createElement("Terrain");

        return terrainElement;
    }

    @Override
    public Terrain load(Element node) {
        if (node == null)
            return null;

        String type = node.getAttribute("type");

        if (type.contains("GRASS"))
            return new GrassTerrain();
        else if (type.contains("WATER"))
            return new WaterTerrain();
        else if (type.contains("MOUNTAIN"))
            return new MountainTerrain();

        return null;
    }
}
