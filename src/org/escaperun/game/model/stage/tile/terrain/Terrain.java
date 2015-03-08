package org.escaperun.game.model.stage.tile.terrain;

import org.escaperun.game.model.Collidable;
import org.escaperun.game.serialization.Savable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Terrain implements Renderable, Savable, Collidable {
    private final Decal decal;

    public Terrain(Decal decal) {
        this.decal = decal;
    }

    /**
     * INTERFACE IMPLEMENTATIONS
     */
    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ this.decal }};
    }

    @Override
    public abstract boolean isCollidable();

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