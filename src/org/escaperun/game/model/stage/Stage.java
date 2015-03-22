package org.escaperun.game.model.stage;

import javafx.geometry.Pos;
import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.npc.NonHostileNPC;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.stage.tile.Tile;
import org.escaperun.game.model.stage.tile.terrain.GrassTerrain;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;
import org.escaperun.game.view.Renderable;

import java.awt.*;
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
    private Avatar avatar;

    public Stage() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }
    //Skill Test
    private ArrayList<Projectile> projectiles;

    public Stage(int rows, int cols) {
        grid = new Tile[rows][cols];
        this.rows = rows;
        this.columns = cols;
        this.entities = new ArrayList<Entity>();

        //Skill Test
        projectiles = new ArrayList<Projectile>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new Tile(new GrassTerrain());
            }
        }
        entities.add(new NonHostileNPC(new Decal('8', Color.BLACK, Color.YELLOW.darker()), new Position(0, 4), 0){
            public void talk(){ //Override of standard "talk"
                Logger.getInstance().pushMessage("ZIP ZOP ZOOPITY BOP");
            }
        });
    }

    public ItemContainer getAvatarInventory() {return avatar.getInventory();}
    public EquipmentContainer getAvatarEquipment() {return avatar.getEquipment();}

    @Override
    public void tick() {
        avatar.tick();
        for (Entity e : entities) {
            e.tick();
        }
        for (Projectile p : projectiles) {
            if (p == null) continue;
            p.tick();
            checkCollision(p);
        }
    }

    public void checkCollision(Projectile p){
        for(Entity e: entities){
            for(int q = 0; q < p.getAffectedArea().size(); q++) {
                if (e.getCurrentPosition().x == p.getAffectedArea().get(q).x && e.getCurrentPosition().y == p.getAffectedArea().get(q).y) {
                    System.out.println("OUCH MAFACKA");
                }
            }
        }
    }

    @Override
    public Decal[][] getRenderable() {
        Decal[][] window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];
        if (GameWindow.ROWS % 2 == 0 || GameWindow.COLUMNS % 2 == 0)
            throw new RuntimeException("view window must be of even dimensions");

        int avatarX = avatar.getCurrentPosition().x;
        int avatarY = avatar.getCurrentPosition().y;
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
            int entX = e.getCurrentPosition().x;
            int entY = e.getCurrentPosition().y;
            int drawX = entX+midX-avatar.getCurrentPosition().x;
            int drawY = entY+midY-avatar.getCurrentPosition().y;
            if (drawX >= 0 && drawY >= 0 &&
                    drawX < window.length && drawY < window[0].length) {
                if (e.getRenderable() != null) {
                    window[drawX][drawY] = e.getRenderable()[0][0];
                }
            }
        }

        //draw Skills
        for(Projectile p: projectiles){
            if (p == null) continue;
            if (p.isDone()) continue;
            for(Position pos : p.getAffectedArea()){
                int drawX = midX+pos.x-avatarX;
                int drawY = midY+pos.y-avatarY;
                if (drawX >= 0 && drawY >= 0 && drawX < window.length && drawY < window[0].length
                        && isValid(pos)) {
                    window[drawX][drawY] = p.getRenderable()[0][0];
                }
            }
        }

        //draw avatar
        if (avatar.getRenderable() != null) {
            window[midX][midY] = avatar.getRenderable()[0][0];
            //25 42
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
            if (entity.getCurrentPosition().equals(pos))
                return false;
        }

        //Check tile collision
        Tile tile = grid[pos.x][pos.y];
        return tile.isCollidable();
    }

    public void addSkill(Projectile p){
        this.projectiles.add(p);
    }

    public void skillCast(){
        this.projectiles.add(this.avatar.skill1());
    }

    public void moveAvatar(Direction dir) {

        //this code is to allow 'sliding' on walls
        //when you press walk along a diagonal
        avatar.move(dir);
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Position getAvatarPosition() {
        return avatar.getCurrentPosition();
    }

    public Entity getEntityextToAvatarsFacingDirection() {
        Direction dir = avatar.getDirection();
        Position avatarpos = avatar.getCurrentPosition();
        Position pos = new Position(avatarpos.x+dir.getDelta().x, avatarpos.y+dir.getDelta().y);
        for(Entity entity : entities){
            if(entity.getCurrentPosition().equals(pos))
                return entity;
        }
        return new NonHostileNPC(null, null, 0){
            public void talk(){
                Logger.getInstance().pushMessage("There is no person to talk to.");
            }
        };
    }
}