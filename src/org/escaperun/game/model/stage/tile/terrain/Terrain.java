package org.escaperun.game.model.stage.tile.terrain;

import org.escaperun.game.model.Collidable;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public abstract class Terrain implements Renderable, Collidable, Saveable {
    private final Decal decal;

    public Terrain(Decal decal) {
        this.decal = decal;
    }

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ this.decal }};
    }

    @Override
    public abstract boolean isCollidable();

    @Override
    public Element save(Document dom, Element parent) {
        Element terrain = dom.createElement("Terrain");
        parent.appendChild(terrain);
        return terrain;
    }

    @Override
    public Terrain load(Element node) {
        if (node == null) return null;
        Element terrain;
        if (node.getElementsByTagName("Terrain") != null &&
                node.getElementsByTagName("Terrain").getLength() > 0)
            terrain = (Element) node.getElementsByTagName("Terrain").item(0);
        else terrain = node;
        if (terrain == null) return null;

        String type = terrain.getAttribute("Type");
        if (type.equals("Blank")) {
            return new BlankTerrain();
        } else if (type.equals("Grass")) {
            return new GrassTerrain();
        } else if (type.equals("Mountain")) {
            return new MountainTerrain();
        } else if (type.equals("Water")) {
            return new WaterTerrain();
        }
        return null;
    }
}
