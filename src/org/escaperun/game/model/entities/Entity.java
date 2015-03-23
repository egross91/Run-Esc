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
        return this;
    }

    @Override
    public void tick() {
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
            inventory.add(toStore);
        }
    }

    public void equipItem(EquipableItem armorItem){
        //equip the item
        if (!inventory.isFull()) {
            EquipableItem equippedItem = equipment.equipItem(armorItem);

            //need to compute the new armor rating for entity so its stats can be updated
            double armorValue = 0.0;
            //this is redic
            EquipableItem body = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.BODY.getSlot());
            EquipableItem helmet = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.HEAD.getSlot());
            EquipableItem boots = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.FEET.getSlot());
            EquipableItem ring = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.RING.getSlot());
            EquipableItem shield = equipment.getItemAtSlot(EquipableItem.EquipmentSlot.SHIELD.getSlot());

            //if an equipment slot has nothing in it, that item will be null. In that case, increment armorValue by 0
            armorValue += (body != null) ? body.getStatistics().getArmorRating().getCurrent() : 0.0;
            armorValue += (helmet != null) ? helmet.getStatistics().getArmorRating().getCurrent() : 0.0;
            armorValue += (boots != null) ? boots.getStatistics().getArmorRating().getCurrent() : 0.0;
            armorValue += (ring != null) ? ring.getStatistics().getArmorRating().getCurrent() : 0.0;
            armorValue += (shield != null) ? shield.getStatistics().getArmorRating().getCurrent() : 0.0;

            statContainer.setArmorValue(armorValue);
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
}
