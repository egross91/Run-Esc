package org.escaperun.game.model.stage;

import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.stage.tile.Tile;
import org.escaperun.game.model.stage.tile.terrain.BlankTerrain;
import org.escaperun.game.serialization.Savable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Stage implements Renderable, Savable, Tickable {

    // from here on out Tiles will refer to things that are
    // local to a specific Position on the grid.
    // Avatars/NPCs/AreaEffects/etc all can be on multi
    // ple tiles
    // and thus will be stored inside STAGE
    // well Avatars/NPCs can move on multiple tiles so there really is
    // no point of putting them on a Tile.
    private Tile[][] grid;
    private int rows, cols;

    public Stage(int rows, int cols) {
        grid = new Tile[rows][cols];
        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new Tile(new BlankTerrain());
            }
        }
    }

    @Override
    public void tick() {
        // for timed effects and npc movement and such
    }

    @Override
    public Decal[][] getRenderable() {
        return null;
    }

    @Override
    public Element save(Document dom) {
        return null;
    }

    @Override
    public Object load(Element node) {
        return null;
    }
}