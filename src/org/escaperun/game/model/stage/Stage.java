package org.escaperun.game.model.stage;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.npc.adversarial.AdversarialNPC;
import org.escaperun.game.model.entities.npc.ai.AI;
import org.escaperun.game.model.entities.npc.nonhostile.NonHostileNPC;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.skills.SkillsContainer;
import org.escaperun.game.model.stage.areaeffect.AreaEffect;
import org.escaperun.game.model.stage.tile.Tile;
import org.escaperun.game.model.stage.tile.terrain.BlankTerrain;
import org.escaperun.game.model.stage.tile.terrain.GrassTerrain;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.awt.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Stage implements Renderable, Tickable, Saveable {

    public static final int DEFAULT_ROWS = 50;
    public static final int DEFAULT_COLUMNS = 85;

    // from here on out Tiles will refer to things that are
    // local to a specific Position on the grid.
    // Avatars/NPCs/AreaEffects/etc all can be on multi
    // ple tiles
    // and thus will be stored inside STAGE
    // well Avatars/NPCs can move on multiple tiles so there really is
    // no point of putting them on a Tile.
    private Tile[][] grid;
    private int rows, columns;
    private ArrayList<AI> ais;
    protected Stack<AI> aiToDelete;
    private ArrayList<NPC> entities;
    private Avatar avatar;
    private ArrayList<AreaEffect> areaEffects;
    //Skill Test
    private ArrayList<Projectile> projectiles;

    @Override
    public Element save(Document dom, Element parent) {
        Element stage = dom.createElement("Stage");
        parent.appendChild(stage);

        stage.setAttribute("Rows", Integer.toString(rows));
        stage.setAttribute("Columns", Integer.toString(columns));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Tile toSave = grid[i][j];
                if (toSave != null) {
                    Element e = toSave.save(dom, stage);
                    e.setAttribute("X", Integer.toString(i));
                    e.setAttribute("Y", Integer.toString(j));
                }
            }
        }
        //TODO: Save rest
        return stage;
    }

    @Override
    public Stage load(Element node) {
        Element stage = (Element) node.getElementsByTagName("Stage").item(0);
        if (stage == null) return null;
        int rows = Integer.parseInt(stage.getAttribute("Rows"));
        int columns = Integer.parseInt(stage.getAttribute("Columns"));
        Stage ret = new Stage(rows, columns);

        NodeList tiles = stage.getElementsByTagName("Tile");
        for (int i = 0; i < tiles.getLength(); i++) {
            Element tile = (Element) tiles.item(i);
            int x = Integer.parseInt(tile.getAttribute("X"));
            int y = Integer.parseInt(tile.getAttribute("Y"));
            Tile put  = new Tile(new BlankTerrain()).load(tile);
            ret.grid[x][y] = put;
        }

        //TODO: Load rest
        return ret;
    }

    public Stage() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public Stage(int rows, int cols) {
        grid = new Tile[rows][cols];
        this.rows = rows;
        this.columns = cols;
        this.entities = new ArrayList<NPC>(1);
        ais = new ArrayList<AI>();
        aiToDelete = new Stack<AI>();

        //Skill Test
        projectiles = new ArrayList<Projectile>();
        //AoE test
        areaEffects = new ArrayList<AreaEffect>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new Tile(new GrassTerrain());
            }
        }
//
//        MeleeNPC anpc = new MeleeNPC(new Decal('U', Color.LIGHT_GRAY, Color.CYAN), new Position(30,30), 1);
//        anpc.setMovementHandler(new MovementHandler(this, anpc, 8));
//        MeleeAI ai = new MeleeAI(this, anpc);
//        addAI(ai);
        //entities.add(anpc);

       // areaEffects.add(new TeleportationAreaEffect(new Decal('?', Color.BLACK, Color.RED.brighter().brighter()),new Position(5,5), new Position(0,0)));
       // grid[10][10].placeItem(new )
    }

    public ItemContainer getAvatarInventory() {return avatar.getInventory();}
    public EquipmentContainer getAvatarEquipment() {return avatar.getEquipment();}
    public SkillsContainer getAvatarSkillsContainer(){ return avatar.getSkillsContainer();}

    @Override
    public void tick() {
        avatar.tick();
        for (Entity e : entities) {
            e.tick();
        }

        for(AreaEffect effect : areaEffects)
            if(avatar.getCurrentPosition().equals(effect.getPosition()))
                effect.onTouch(avatar);

        for (int r = 0; r < projectiles.size(); r++) {
            if (projectiles.get(r) == null) continue;
            if (projectiles.get(r).isDone()){
                projectiles.remove(r);
                r--;
                continue;
            }
            projectiles.get(r).tick();
            if(checkCollision(projectiles.get(r))){
                //do damage and shiit
                projectiles.remove(r);
                r--;
            }
        }

        for (int i = 0; i < ais.size(); i++) {
            ais.get(i).tick();
        }
    }

    public boolean checkCollision(Projectile p){
        for(int e = 0; e < entities.size(); e++){
            for(int q = 0; q < p.getAffectedArea().size(); q++) {
                if (entities.get(e).getCurrentPosition().x == p.getAffectedArea().get(q).x && entities.get(e).getCurrentPosition().y == p.getAffectedArea().get(q).y) {
                    if(!(entities.get(e).takeDamage(p.generateSuccess(p.getOwner(), entities.get(e))))){
                        this.getAvatar().gainXP(entities.get(e).getXPworth());
                        //entities.remove(e);
                        //e--;
                    }
                    return true;
                }
            }
        }
        return false;
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
            if (p == null){ continue;}
            if (p.isDone()){ continue;}
            for(Position pos : p.getAffectedArea()){
                int drawX = midX+pos.x-avatarX;
                int drawY = midY+pos.y-avatarY;
                if (drawX >= 0 && drawY >= 0 && drawX < window.length && drawY < window[0].length
                        && isValid(pos)) {
                    window[drawX][drawY] = p.getRenderable()[0][0];
                }
            }
        }

        //draw (visible) Area Effects
        //TODO: Make it such that some AoEs are invisible to anyone who cannot do Detect.
        for(AreaEffect effect : areaEffects){
            Position pos = effect.getPosition();
            int drawX = pos.x+midX-avatar.getCurrentPosition().x;
            int drawY = pos.y+midY-avatar.getCurrentPosition().y;
            window[drawX][drawY] = effect.getRenderable()[0][0];
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
        if (tile == null) {
            return false;
        }
        return tile.isCollidable();
    }

    public void addSkill(Projectile p){
        this.projectiles.add(p);
    }

    public void skillCast(){
        //moved this to Avatar because it was a violation of TDA
        if(avatar.attemptSkillCast()) { //casting the spell is OK
            this.projectiles.add(this.avatar.skill1());
        }else Logger.getInstance().pushMessage("You are out of mana!");
        //otherwise dont cast that bitch
    }

    public void moveAvatar(Direction dir) {
        //this code is to allow 'sliding' on walls
        //when you press walk along a diagonal
        avatar.move(dir);
    }

    public Avatar getAvatar() {
        return this.avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Position getAvatarPosition() {
        return avatar.getCurrentPosition();
    }

    public void interactionTriggered() {
        Direction dir = avatar.getDirection();
        Position avatarpos = avatar.getCurrentPosition();
        Position pos = new Position(avatarpos.x+dir.getDelta().x, avatarpos.y+dir.getDelta().y);
        for(Entity entity : entities){
            if(entity.getCurrentPosition().equals(pos)) {
                entity.talk();
                return;
            }
        }
        Logger.getInstance().pushMessage("There is not an entity to talk to.");
    }

    /** Should only be accessed by AI. And not while ticking.*/
    public void addAI(AI ai) {
        if (!ais.contains(ai)) {
            entities.add(ai.getNpc());
            ais.add(ai);
        }
    }

    /** The AI and the associated npc will be removed on tick.*/
    public void aiToRemove(AI ai) {
        entities.remove(ai.getNpc());
        ais.remove(ai);
    }
}