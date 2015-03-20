package org.escaperun.game.model.stage;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.stage.tile.Tile;
import org.escaperun.game.model.stage.tile.terrain.GrassTerrain;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;
import org.escaperun.game.view.Renderable;

import java.util.ArrayList;

public class Stage implements Renderable, Tickable {

    public static final int DEFAULT_ROWS = 50;
    public static final int DEFAULT_COLUMNS = 50;

    // from here on out Tiles will refer to things that are
    // local to a specific Position on the grid.
    // Avatars/NPCs/AreaEffects/etc all can be on multi
    // ple tiles
    // and thus will be stored inside STAGE
    // well Avatars/NPCs can move on multiple tiles so there really is
    // no point of putting them on a Tile.
    private Tile[][] grid;
    private int rows, columns;
    private ArrayList<Entity> entities;
    private final Avatar avatar;

    public Stage(Avatar avatar) {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS, avatar);
    }

    public Stage(int rows, int cols, Avatar avatar) {
        grid = new Tile[rows][cols];
        this.rows = rows;
        this.columns = cols;
        this.avatar = avatar;
        this.entities = new ArrayList<Entity>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new Tile(new GrassTerrain());
            }
        }
    }

    @Override
    public void tick() {
        avatar.tick();
        for (Entity e : entities) {
            e.tick();
        }
    }

    @Override
    public Decal[][] getRenderable() {
        Decal[][] window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];
        if (GameWindow.ROWS % 2 == 0 || GameWindow.COLUMNS % 2 == 0)
            throw new RuntimeException("view window must be of even dimensions");

        int avatarX = avatar.getPosition().x;
        int avatarY = avatar.getPosition().y;
        int midX = GameWindow.ROWS/2;
        int midY = GameWindow.COLUMNS/2;
        //draw tiles
        for (int i = -midX; i <= +midX; i++) {
            for (int j = -midY; j <= +midY; j++) {
                if (i == 0 && j == 0) continue;
                int toX = avatarX+i;
                int toY = avatarY+j;
                if (isValid(new Position(toX, toY))
                        && grid[toX][toY] != null
                        && grid[toX][toY].getRenderable() != null) {
                    window[midX+i][midY+j] = grid[toX][toY].getRenderable()[0][0];
                }
            }
        }

        //draw entities
        for (Entity e : entities) {
            int entX = e.getPosition().x;
            int entY = e.getPosition().y;
            int drawX = entX-midX;
            int drawY = entY-midY;
            if (drawX >= 0 && drawY >= 0 &&
                    drawX < window.length && drawY < window[0].length) {
                if (e.getRenderable() != null) {
                    window[drawX][drawY] = e.getRenderable()[0][0];
                }
            }
        }

        //draw avatar
        if (avatar.getRenderable() != null) {
            window[midX][midY] = avatar.getRenderable()[0][0];
        }
        return window;
    }

    public boolean isValid(Position pos) {
        return pos.x >= 0 && pos.x < rows && pos.y >= 0 && pos.y < columns;
    }

    public boolean isMoveable(Position pos) {
        //Check valid position
        if (!isValid(pos))
            return false;

        //Check entity collision
        for (Entity entity : entities) {
            if (entity.getPosition().equals(pos))
                return false;
        }

        //Check tile collision
        Tile tile = grid[pos.x][pos.y];
        return tile.isCollidable();
    }

    public void moveAvatar(Direction dir) {
        //this code is to allow 'sliding' on walls
        //when you press walk along a diagonal

        avatar.setDirection(dir);

        Position first = new Position(avatar.getPosition().x+dir.getDelta().x, avatar.getPosition().y);

        if (isMoveable(first)) {
            avatar.setPosition(first);
        }

        Position second = new Position(avatar.getPosition().x, avatar.getPosition().y+dir.getDelta().y);

        if (isMoveable(second)) {
            avatar.setPosition(second);
        }

    }
}