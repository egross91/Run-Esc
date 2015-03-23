package org.escaperun.game.model.entities;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.handlers.MovementHandler;
import org.escaperun.game.model.entities.handlers.RestorationHandler;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.entities.skills.SkillsContainer;
import org.escaperun.game.model.entities.statistics.IStatSubscriber;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponVisitor;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Renderable;

import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public abstract class Entity implements Renderable, Tickable, WeaponVisitor, Saveable {
    private Position currentPosition = null;
    private EquipmentContainer<EquipableItem> equipment;
    private ItemContainer<TakeableItem> inventory;
    private Position initialPosition;
    private Decal decal;
    private Direction direction;
    private MovementHandler movementHandler;
    private RestorationHandler restorationHandler;
    private StatisticContainer statContainer;

    protected Entity(Decal decal, Position initialPosition) {
        this.initialPosition = initialPosition;
        this.currentPosition = initialPosition;
        this.decal = decal;
        direction = Direction.EAST;
        inventory = new ItemContainer<TakeableItem>();
        equipment = new EquipmentContainer<EquipableItem>();
        statContainer = new StatisticContainer();
        restorationHandler = new RestorationHandler(this, 400);
        statSubscribe();
    }

    public void statSubscribe() {
        equipment.subscribe(statContainer.getAgility());
        equipment.subscribe(statContainer.getStrength());
        equipment.subscribe(statContainer.getIntellect());
        equipment.subscribe(statContainer.getHardiness());
        equipment.subscribe(statContainer.getMovement());
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element ent = dom.createElement("Entity");
        parent.appendChild(ent);
        ent.setAttribute("InitialX", Integer.toString(initialPosition.x));
        ent.setAttribute("InitialY", Integer.toString(initialPosition.y));
        ent.setAttribute("CurrentX", Integer.toString(currentPosition.x));
        ent.setAttribute("CurrentY", Integer.toString(currentPosition.y));
        ent.setAttribute("Direction", direction.toString());
        decal.save(dom, ent);
        statContainer.save(dom, ent);
        equipment.save(dom, ent);
        inventory.save(dom, ent);
        return ent;
    }

    @Override
    public Entity load(Element node) {
        if (node == null) return null;
        Element us = node;
        int initX = Integer.parseInt(us.getAttribute("InitialX"));
        int initY = Integer.parseInt(us.getAttribute("InitialY"));
        int curX = Integer.parseInt(us.getAttribute("CurrentX"));
        int curY = Integer.parseInt(us.getAttribute("CurrentY"));
        Direction dir = Direction.valueOf(us.getAttribute("Direction"));
        this.initialPosition = new Position(initX, initY);
        this.currentPosition = new Position(curX, curY);
        setDirection(dir);
        this.decal = Decal.BLANK.load(us);
        this.statContainer = new StatisticContainer().load(us);
        this.equipment = new EquipmentContainer<EquipableItem>().load(us);
        this.inventory = new ItemContainer<TakeableItem>().load(us);
        statSubscribe();
        return this;
    }

    @Override
    public void tick() {
        if (movementHandler == null) return;
        movementHandler.tick();
        restorationHandler.tick();
    }

    public StatisticContainer getStatContainer(){
        return statContainer;
    }

    public void move(Direction dir) {
        movementHandler.move(dir);
    }

    public void takeItem(TakeableItem item) {
        inventory.add(item);
        Logger.getInstance().pushMessage("Put the " + item.getName() + " into inventory.");
    }

    public void equipItem(WeaponItem weaponItem) {
        if (!inventory.isFull()) {
            EquipableItem equippedItem = equipment.equipItem(weaponItem);
            statContainer.setWeaponDamage((equippedItem != null) ? equippedItem.getStatistics().getOffensiveRating().getCurrent() : 0.0);
            inventory.add(equippedItem);
        }
    }

    public void unequipItem(EquipableItem item) {
        if (!inventory.isFull()) {
            EquipableItem toStore = equipment.unequipItem(item.getEquipmentSlot());
            recalStatistics(true);
            inventory.add(toStore);
        }
    }

    public void equipItem(EquipableItem armorItem){
        //equip the item
        if (!inventory.isFull()) {
            EquipableItem equippedItem = equipment.equipItem(armorItem);
            recalStatistics(false);
            inventory.add(equippedItem);
        }
    }

    public abstract void attack(Entity defender, Skill skill);

    public EquipmentContainer<EquipableItem> getEquipment() {
        return this.equipment;
    }

    public Position getCurrentPosition(){
        return currentPosition; //Return currentPosition of this entity
    }

    public Position getInitialPosition(){
        return initialPosition;
    }

    public ItemContainer<TakeableItem> getInventory() {
        return this.inventory;
    }

    public void setPosition(Position p){
        currentPosition = p; //Set currentPosition of this entity (only should be used with move()).
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setMovementHandler(MovementHandler movementHandler) {
        this.movementHandler = movementHandler;
    }

    public Direction getDirection(){
        return direction;
    }

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ decal }}; //Return a 1x1 decal array.
    }

    public abstract void talk();

    public boolean isDead() {   // would be nice to get rid of this method and replace with a eventlistener.
        return (statContainer.getLivesLeft().getBase() <= 0);
    }

    public boolean lostLife(){return (statContainer.getLife().getCurrent() <= 0);}

    public double getMovementPoints() {
        return statContainer.getMovement().getBase();
    }

    protected void setStatContainer(StatisticContainer statContainer) {
        this.statContainer = statContainer;
    }

    public boolean takeDamage(double amount) {
        //return false if dead.
        this.getStatContainer().getLife().takeDamage((int)amount);
        if(this.lostLife()){
            return false;
        }
        return true;
    }

    public void subscribeToDeath(IStatSubscriber subscriber) {
        statContainer.subscribeToLivesLeft(subscriber);
    }

    public void unsubscriveToDeath(IStatSubscriber subscriber) {
        statContainer.unsubcribeToLivesLeft(subscriber);
    }

    private void recalStatistics(boolean sub) {
        // Not proud of this, just trying to make it work at this point.
        //need to compute the new armor rating for entity so its stats can be updated
        //this is redic
        /*EquipableItem body = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.BODY.getSlot());
        EquipableItem helmet = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.HEAD.getSlot());
        EquipableItem boots = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.FEET.getSlot());
        EquipableItem ring = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.RING.getSlot());
        EquipableItem shield = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.SHIELD.getSlot());
        EquipableItem weapon = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.WEAPON.getSlot());

        //if an equipment slot has nothing in it, that item will be null. In that case, increment armorValue by 0
        double armorValue = 0.0;
        armorValue += (body != null) ? body.getStatistics().getArmorRating().getCurrent() : 0.0;
        armorValue += (helmet != null) ? helmet.getStatistics().getArmorRating().getCurrent() : 0.0;
        armorValue += (boots != null) ? boots.getStatistics().getArmorRating().getCurrent() : 0.0;
        armorValue += (ring != null) ? ring.getStatistics().getArmorRating().getCurrent() : 0.0;
        armorValue += (shield != null) ? shield.getStatistics().getArmorRating().getCurrent() : 0.0;

        double mana = 0.0;
        mana += (body != null) ? body.getStatistics().getMana().getCurrent() : 0.0;
        mana += (helmet != null) ? helmet.getStatistics().getMana().getCurrent() : 0.0;
        mana += (boots != null) ? boots.getStatistics().getMana().getCurrent() : 0.0;
        mana += (ring != null) ? ring.getStatistics().getMana().getCurrent() : 0.0;
        mana += (shield != null) ? shield.getStatistics().getMana().getCurrent() : 0.0;
        mana += (shield != null) ? weapon.getStatistics().getMana().getCurrent() : 0.0;

        double offenseRating = 0.0;
        offenseRating += (weapon != null) ? weapon.getStatistics().getOffensiveRating().getCurrent() : 0.0;

        double agility = 0.0;
        agility += (body != null) ? body.getStatistics().getAgility().getCurrent() : 0.0;
        agility += (helmet != null) ? helmet.getStatistics().getAgility().getCurrent() : 0.0;
        agility += (boots != null) ? boots.getStatistics().getAgility().getCurrent() : 0.0;
        agility += (ring != null) ? ring.getStatistics().getAgility().getCurrent() : 0.0;
        agility += (shield != null) ? shield.getStatistics().getAgility().getCurrent() : 0.0;
        agility += (shield != null) ? weapon.getStatistics().getAgility().getCurrent() : 0.0;

        double hardiness = 0.0;
        hardiness += (body != null) ? body.getStatistics().getHardiness().getCurrent() : 0.0;
        hardiness += (helmet != null) ? helmet.getStatistics().getHardiness().getCurrent() : 0.0;
        hardiness += (boots != null) ? boots.getStatistics().getHardiness().getCurrent() : 0.0;
        hardiness += (ring != null) ? ring.getStatistics().getHardiness().getCurrent() : 0.0;
        hardiness += (shield != null) ? shield.getStatistics().getHardiness().getCurrent() : 0.0;
        hardiness += (shield != null) ? weapon.getStatistics().getHardiness().getCurrent() : 0.0;

        double movement = 0.0;
        movement += (body != null) ? body.getStatistics().getMovement().getCurrent() : 0.0;
        movement += (helmet != null) ? helmet.getStatistics().getMovement().getCurrent() : 0.0;
        movement += (boots != null) ? boots.getStatistics().getMovement().getCurrent() : 0.0;
        movement += (ring != null) ? ring.getStatistics().getMovement().getCurrent() : 0.0;
        movement += (shield != null) ? shield.getStatistics().getMovement().getCurrent() : 0.0;
        movement += (shield != null) ? weapon.getStatistics().getMovement().getCurrent() : 0.0;

        double defenseRating = 0.0;
        defenseRating += (body != null) ? body.getStatistics().getDefensiveRating().getCurrent() : 0.0;
        defenseRating += (helmet != null) ? helmet.getStatistics().getDefensiveRating().getCurrent() : 0.0;
        defenseRating += (boots != null) ? boots.getStatistics().getDefensiveRating().getCurrent() : 0.0;
        defenseRating += (ring != null) ? ring.getStatistics().getDefensiveRating().getCurrent() : 0.0;
        defenseRating += (shield != null) ? shield.getStatistics().getDefensiveRating().getCurrent() : 0.0;
        defenseRating += (shield != null) ? weapon.getStatistics().getDefensiveRating().getCurrent() : 0.0;

        double intellect = 0.0;
        intellect += (body != null) ? body.getStatistics().getIntellect().getCurrent() : 0.0;
        intellect += (helmet != null) ? helmet.getStatistics().getIntellect().getCurrent() : 0.0;
        intellect += (boots != null) ? boots.getStatistics().getIntellect().getCurrent() : 0.0;
        intellect += (ring != null) ? ring.getStatistics().getIntellect().getCurrent() : 0.0;
        intellect += (shield != null) ? shield.getStatistics().getIntellect().getCurrent() : 0.0;
        intellect += (shield != null) ? weapon.getStatistics().getIntellect().getCurrent() : 0.0;

        if (!sub) {
            statContainer.setCurrent(armorValue, mana, offenseRating, agility, hardiness,
                    movement, defenseRating, intellect);
        }
        else {
            statContainer.setCurrent(-armorValue, -mana, -offenseRating, -agility, -hardiness,
                    -movement, -defenseRating, -intellect);
        }*/
    }
}

