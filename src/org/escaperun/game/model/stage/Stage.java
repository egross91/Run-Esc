package org.escaperun.game.model.stage;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.controller.Sound;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.*;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.handlers.MovementHandler;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.npc.adversarial.AdversarialNPC;
import org.escaperun.game.model.entities.npc.adversarial.MeleeNPC;
import org.escaperun.game.model.entities.npc.adversarial.RangedNPC;
import org.escaperun.game.model.entities.npc.ai.*;
import org.escaperun.game.model.entities.npc.nonhostile.CitizenNPC;
import org.escaperun.game.model.entities.npc.nonhostile.ShopkeepingNPC;
import org.escaperun.game.model.entities.skills.*;
import org.escaperun.game.model.entities.statistics.IStatSubscriber;
import org.escaperun.game.model.entities.npc.nonhostile.NonHostileNPC;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.stage.areaeffect.*;
import org.escaperun.game.model.stage.tile.Tile;
import org.escaperun.game.model.stage.tile.terrain.BlankTerrain;
import org.escaperun.game.model.stage.tile.terrain.GrassTerrain;
import org.escaperun.game.model.stage.tile.terrain.Terrain;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Stage implements Renderable, Tickable, Saveable, IStatSubscriber {

    public static final int DEFAULT_ROWS = 200;//50
    public static final int DEFAULT_COLUMNS = 250;//85

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
    private ArrayList<ActiveSkill> activeSkills;
    private ArrayList<PassiveSkill> passives;
    private int passiveTimer = 5;
   // private Timer moveAmount;
    private Timer moveTimer;

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
        avatar.save(dom, stage);
        for (NPC npc : entities) {
            npc.save(dom, stage);
        }
        for (AreaEffect af : areaEffects) {
            af.save(dom, stage);
        }
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

        if (node.getElementsByTagName("Smasher").getLength() > 0) {
            Smasher smash = new Smasher(null).load((Element)node.getElementsByTagName("Smasher").item(0));
            ret.setAvatar(smash);
            ret.getAvatar().setMovementHandler(new MovementHandler(ret, ret.getAvatar(), 8));
        } else if (node.getElementsByTagName("Sneak").getLength() > 0) {
            Sneak sneak = new Sneak(null).load((Element)node.getElementsByTagName("Sneak").item(0));
            ret.setAvatar(sneak);
            ret.getAvatar().setMovementHandler(new MovementHandler(ret, ret.getAvatar(), 8));
        } else if (node.getElementsByTagName("Summoner").getLength() > 0) {
            Summoner summoner = new Summoner(null).load((Element)node.getElementsByTagName("Summoner").item(0));
            ret.setAvatar(summoner);
            ret.getAvatar().setMovementHandler(new MovementHandler(ret, ret.getAvatar(), 8));
        }
        NodeList mel = node.getElementsByTagName("MeleeNPC");
        for (int i = 0; i < mel.getLength(); i++) {
            Element melNpc = (Element) mel.item(i);
            MeleeNPC npc = new MeleeNPC(Decal.BLANK, new Position(0, 0), 0).load(melNpc);
            npc.setMovementHandler(new MovementHandler(ret, npc, 8));
            new MeleeAI(ret, npc);
        }
        NodeList ran = node.getElementsByTagName("RangedNPC");
        for (int i = 0; i < ran.getLength(); i++) {
            Element ranNpc = (Element) ran.item(i);
            RangedNPC npc = new RangedNPC(Decal.BLANK, new Position(0, 0), 0).load(ranNpc);
            npc.setMovementHandler(new MovementHandler(ret, npc, 8));
            new RangedAI(ret, npc);
        }
        NodeList cit = node.getElementsByTagName("CitizenNPC");
        for (int i = 0; i < cit.getLength(); i++) {
            Element citNpc = (Element) cit.item(i);
            CitizenNPC npc = new CitizenNPC(Decal.BLANK, new Position(0, 0), 0).load(citNpc);
            npc.setMovementHandler(new MovementHandler(ret, npc, 8));
            new CitizenAI(ret, npc);
        }
        NodeList shop = node.getElementsByTagName("ShopkeepingNPC");
        for (int i = 0; i < shop.getLength(); i++) {
            Element shopNpc = (Element) shop.item(i);
            ShopkeepingNPC npc = new ShopkeepingNPC(Decal.BLANK, new Position(0, 0), 0).load(shopNpc);
            npc.setMovementHandler(new MovementHandler(ret, npc, 8));
            ret.entities.add(npc);
            //TODO:TODO
        }
        /* load area effects*/
        NodeList hds = node.getElementsByTagName("HealDamage");
        for (int i = 0; i < hds.getLength(); i++) {
            Element hdEle = (Element) hds.item(i);
            HealDamage hd = new HealDamage(Decal.BLANK, new Position(0,0)).load(hdEle);
            ret.areaEffects.add(hd);
        }
        hds = node.getElementsByTagName("InstantDeath");
        for (int i = 0; i < hds.getLength(); i++) {
            Element hdEle = (Element) hds.item(i);
            InstantDeath hd = new InstantDeath(Decal.BLANK, new Position(0,0)).load(hdEle);
            ret.areaEffects.add(hd);
        }
        hds = node.getElementsByTagName("LevelUp");
        for (int i = 0; i < hds.getLength(); i++) {
            Element hdEle = (Element) hds.item(i);
            LevelUp hd = new LevelUp(Decal.BLANK, new Position(0,0)).load(hdEle);
            ret.areaEffects.add(hd);
        }
        hds = node.getElementsByTagName("TakeDamage");
        for (int i = 0; i < hds.getLength(); i++) {
            Element hdEle = (Element) hds.item(i);
            TakeDamage hd = new TakeDamage(Decal.BLANK, new Position(0,0)).load(hdEle);
            ret.areaEffects.add(hd);
        }
        hds = node.getElementsByTagName("TeleportationAreaEffect");
        for (int i = 0; i < hds.getLength(); i++) {
            Element hdEle = (Element) hds.item(i);
            TeleportationAreaEffect hd = new TeleportationAreaEffect(Decal.BLANK, new Position(0,0), new Position(0,0)).load(hdEle);
            ret.areaEffects.add(hd);
        }
        return ret;
    }

    public void setTerrainAt(Terrain t) {
        grid[avatar.getCurrentPosition().x][avatar.getCurrentPosition().y].setTerrain(t);
    }

    public void addTeleport() {
        areaEffects.add(new TeleportationAreaEffect(new Decal('?', Color.BLACK, Color.BLUE.brighter().brighter()), avatar.getCurrentPosition(), new Position(0,0)));
    }

    public void addLevelUp() {
        areaEffects.add(new LevelUp(new Decal('*', Color.BLACK, Color.ORANGE.darker()), avatar.getCurrentPosition()));
    }

    public void addRanged() {
        entities.add(new RangedNPC(new Decal('M', Color.BLACK, Color.GREEN.darker().darker()), avatar.getCurrentPosition(), 8));
    }

    public void addMelee() {
        entities.add(new MeleeNPC(new Decal('M', Color.BLACK, Color.RED.darker().darker()), avatar.getCurrentPosition(), 8));
    }

    public void addShop() {
        entities.add(new ShopkeepingNPC(new Decal('$', Color.BLACK, Color.WHITE), avatar.getCurrentPosition(), 3));
    }

    public void addCitizen() {
        entities.add(new CitizenNPC(new Decal('C', Color.BLACK, Color.WHITE), avatar.getCurrentPosition(), 3));
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
        activeSkills = new ArrayList<ActiveSkill>();
        //AoE test
        areaEffects = new ArrayList<AreaEffect>();

        // For Observation
        passives = new ArrayList<PassiveSkill>();
        passives.add(new Observe(1, this.getAvatar()));

        this.moveTimer = new Timer(10);
       // this.moveAmount = new Timer(10);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new Tile(new BlankTerrain());
            }
        }
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
        ArrayList<AreaEffect> keep = new ArrayList<AreaEffect>();
        for(AreaEffect effect : areaEffects)
            if(avatar.getCurrentPosition().equals(effect.getPosition())) {
                effect.onTouch(avatar);
                if (!effect.selfDestruct()) keep.add(effect);
            } else
                keep.add(effect);

        this.areaEffects = keep;

        for (int r = 0; r < activeSkills.size(); r++) {
            if (activeSkills.get(r) == null) continue;
            if (activeSkills.get(r).isDone()){
                activeSkills.remove(r);
                r--;
                continue;
            }
            activeSkills.get(r).tick();
            if (activeSkills.get(r) instanceof Projectile){ //lol @ hax
                if (checkCollision(((Projectile) activeSkills.get(r)))) {
                    //do damage and shiit
                    activeSkills.remove(r);
                    r--;
                }
            }
        }

        passiveCheck();

        for (int i = 0; i < ais.size(); i++) {
            ais.get(i).tick();
        }
    }

    public void passiveCheck(){
       // if (moveAmount.isDone()) return;
        moveTimer.tick();
        if (!moveTimer.isDone()) return;
        moveTimer.reset();
       // moveAmount.tick();
        for(int h = 0; h < passives.size(); h++){
            passives.get(h).setCurrentPos(getAvatarPosition());
            passives.get(h).tick();

            for(int e = 0; e < entities.size(); e++){
                for(int p = 0; p < passives.get(h).getAffectedArea().size(); p++){
                    if(entities.get(e).getCurrentPosition().x == passives.get(h).getAffectedArea().get(p).x &&
                            entities.get(e).getCurrentPosition().y == passives.get(h).getAffectedArea().get(p).y){
                            Logger.getInstance().pushRightMessage(passives.get(h).doshIt(entities.get(e), passives.get(h).getMoveAmount()));
                    }
                }
            }

        }
    }

    public boolean nextToShop() {
        Direction dir = avatar.getDirection();
        Position avatarpos = avatar.getCurrentPosition();
        Position pos = new Position(avatarpos.x+dir.getDelta().x, avatarpos.y+dir.getDelta().y);
        for(Entity entity : entities){
            if(entity.getCurrentPosition().equals(pos) && (entity instanceof ShopkeepingNPC)) {
                Logger.getInstance().pushMessage("You talk to the shopkeeper!");
                return true;
            }
        }
        Logger.getInstance().pushMessage("There is no shopkeeper around :(");
        return false;
    }

    public boolean checkCollision(Projectile p){
        //Check avatar
        for(int q = 0; q < p.getAffectedArea().size(); q++) {
            if (avatar.getCurrentPosition().x == p.getAffectedArea().get(q).x && avatar.getCurrentPosition().y == p.getAffectedArea().get(q).y) {
                //hit
                if (p.getOwner() != avatar) {
                    avatar.takeDamage(p.generateSuccess(p.getOwner(), avatar, p.getMoveAmount()));
                }
                return true;
            }
        }

        //Check npc
        for(int e = 0; e < entities.size(); e++){
            Entity entity = entities.get(e);
            for(int q = 0; q < p.getAffectedArea().size(); q++) {
                if (entity.getCurrentPosition().x == p.getAffectedArea().get(q).x && entity.getCurrentPosition().y == p.getAffectedArea().get(q).y) {
                    if (p.getOwner() != entity) {
                        double val = p.generateSuccess(p.getOwner(), entities.get(e), p.getMoveAmount());
                        if (!(entities.get(e).takeDamage(val))) {
                            this.getAvatar().gainXP(entities.get(e).getXPworth());
                        }
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
        for(ActiveSkill a: activeSkills){
            if(a instanceof Projectile) {
                Projectile p = (Projectile) a;
                if (p == null) {
                    continue;
                }
                if (p.isDone()) {
                    continue;
                }
                for (Position pos : p.getAffectedArea()) {
                    int drawX = midX + pos.x - avatarX;
                    int drawY = midY + pos.y - avatarY;
                    if (drawX >= 0 && drawY >= 0 && drawX < window.length && drawY < window[0].length
                            && isValid(pos)) {
                        window[drawX][drawY] = p.getRenderable()[0][0];
                    }
                }
            }
        }

        //draw (visible) Area Effects
        //TODO: Make it such that some AoEs are invisible to anyone who cannot do Detect.
        for(AreaEffect effect : areaEffects){
            Position pos = effect.getPosition();
            int drawX = pos.x+midX-avatar.getCurrentPosition().x;
            int drawY = pos.y+midY-avatar.getCurrentPosition().y;
            if (drawX >= 0 && drawY >= 0 && drawX < window.length && drawY < window[0].length) {
                window[drawX][drawY] = effect.getRenderable()[0][0];
            }
        }

        //draw avatar
        if (avatar.getRenderable() != null) {
            window[midX][midY] = avatar.getRenderable()[0][0];
            //25 42
        }
        return window;
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
        return tile.isCollidable() && true;
    }

    public void addActiveSkill(ActiveSkill activeSkill){
        this.activeSkills.add(activeSkill);
    }

    public void skillCast1(){
        ActiveSkill skillCast = avatar.attemptSkillCast1(Logger.getInstance());
        if(skillCast != null) { //casting the spell went OK
            Sound.CASTSPELL.play();
            this.activeSkills.add(skillCast);
        }
    }

    public void skillCast2(){
        ActiveSkill skillCast = avatar.attemptSkillCast2(Logger.getInstance());
        if(skillCast != null) { //casting the spell went OK
            Sound.CASTSPELL.play();
            this.activeSkills.add(skillCast);
        }
    }

    public void skillCast3(){
        ActiveSkill skillCast = avatar.attemptSkillCast3(Logger.getInstance());
        if(skillCast != null) { //casting the spell went OK
            Sound.CASTSPELL.play();
            this.activeSkills.add(skillCast);
        }
    }

    public void skillCast4(){
        ActiveSkill skillCast = avatar.attemptSkillCast4(Logger.getInstance());
        if(skillCast != null) { //casting the spell went OK
            Sound.CASTSPELL.play();
            this.activeSkills.add(skillCast);
        }
    }

    public void moveAvatar(Direction dir) {
        //this code is to allow 'sliding' on walls
        //when you press walk along a diagonal
        avatar.move(dir);
    }

    /** Do not use for outside of stage. */
    public Avatar getAvatar() {
        return this.avatar;
    }

    /** Unsubscribe to avatar death, return avatar and null avatar variable. */
    public Avatar removeAvatar() {
        avatar.unsubscriveToDeath(this);
        Avatar temp = avatar;
        avatar = null;
        return temp;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
        avatar.subscribeToDeath(this);
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

    /** Notified of Avatar death. */
    @Override
    public void notifyChange() {
        //Check if zero lives
        int lives = avatar.getAvatarStatistics().getLivesLeft().getCurrent();
        if (lives <= 0) {
            //TODO: code for when avatar loses all lives.
            throw new RuntimeException("YOU HAVE DIED! However a death screen has not been implemented yet.");
        }

        //Reset avatar
        Logger.getInstance().pushMessage("YOU HAVE DIED!! Try again.");
        avatar.getStatContainer().getLife().refillLife();
        if (isMoveable(avatar.getInitialPosition()))
            avatar.setPosition(avatar.getInitialPosition());
        //TODO: Find the next closes area to respawn
    }

    private boolean isValid(Position pos) {
        return pos.x >= 0 && pos.x < rows && pos.y >= 0 && pos.y < columns;
    }
}