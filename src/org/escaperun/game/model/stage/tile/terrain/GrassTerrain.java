package org.escaperun.game.model.stage.tile.terrain;

import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.util.Random;

public class GrassTerrain extends Terrain {

    private static final Random RANDOM = new Random();

    public GrassTerrain() {
        super(new Decal('#', Color.BLACK, RANDOM_GREEN()));
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
        terrain.setAttribute("Type", "Grass");
        return terrain;
    }

    private static final Color RANDOM_GREEN() {
        int next = RANDOM.nextInt(5);
        if (next == 0) {
            return Color.GREEN.darker();
        }
        else if (next == 1) {
            return Color.GREEN.darker().darker();
        }
        else if (next == 2) {
            return Color.GREEN.darker().darker().darker();
        }
        else if (next == 3) {
            return Color.GREEN.darker().darker();
        }
        else if (next == 4) {
            return Color.GREEN.darker().darker().darker();
        }

        return null;
    }
}
