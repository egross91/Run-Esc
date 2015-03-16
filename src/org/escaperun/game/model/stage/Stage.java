package org.escaperun.game.model.stage;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.stage.tile.Tile;
import org.escaperun.game.model.stage.tile.terrain.BlankTerrain;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

import java.util.ArrayList;

public class Stage implements Renderable, Tickable {

    // from here on out Tiles will refer to things that are
    // local to a specific Position on the grid.
    // Avatars/NPCs/AreaEffects/etc all can be on multi
    // ple tiles
    // and thus will be stored inside STAGE
    // well Avatars/NPCs can move on multiple tiles so there really is
    // no point of putting them on a Tile.
    private Tile[][] grid;
    private int rows, cols;
    private ArrayList<NPC> NPCs;
    private final Avatar avatar;

    public Stage(int rows, int cols, Avatar avatar) {
        grid = new Tile[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.avatar = avatar;
        this.NPCs = new ArrayList<NPC>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new Tile(new BlankTerrain());
            }
        }
    }

    /**
     * Update anything on stage what moves or must make a decision.
     */
    public void update() {
        updateNpcLocations();
    }

    private void updateNpcLocations() {
        for (NPC npc : NPCs) {
            npc.updateLocation(this);
        }
    }

    /**
     *  Check it is if there is any other enitiy is at that position or if the tile is contains a collidable.
     *  If method returns true, method assumes that NPC will set position to desired position.
     */
    public boolean isValidMove(Position desiredPosition) {
        //Check vaild position
        if (desiredPosition.x > rows || desiredPosition.y > cols)
            return false;

        //Check NPC collision.
        for (NPC npc: NPCs) {
            if (npc.getCurrentPosition().equals(desiredPosition))
                return false;
        }

        //Check tile collisions
        Tile tile = grid[desiredPosition.x][desiredPosition.y];
        return tile.isCollidable();
    }

    @Override
    public void tick() {
        // for timed effects and npc movement and such
    }

    @Override
    public Decal[][] getRenderable() {
        return null;
    }

    public boolean moveAvatar(Position position) {
        if (!isValidMove(position)) {
            return false;
        }

        //Check tile collisions
        if (avatar.tryMove(position)) {
            //TODO: Apply onTouch from tile recently moved on.
            return true;
        }

        return false;
    }
}