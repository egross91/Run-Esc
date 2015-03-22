package org.escaperun.game.model.entities;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.handlers.MovementHandler;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.Equipable;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponVisitor;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Renderable;

import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;


public abstract class Entity implements Renderable, Tickable, WeaponVisitor {
    private Position currentPosition = null;
    private EquipmentContainer<EquipableItem> equipment;
    private ItemContainer<TakeableItem> inventory;
    private final Position initialPosition;
    private final Decal decal;
    private Direction direction;
    private MovementHandler movementHandler;
    private StatisticContainer statContainer;

    protected Entity(Decal decal, Position initialPosition) {
        this.initialPosition = initialPosition;
        this.currentPosition = initialPosition;
        this.decal = decal;
        direction = Direction.EAST;
        inventory = new ItemContainer<TakeableItem>();
        equipment = new EquipmentContainer<EquipableItem>();
//        statContainer = new StatisticContainer();
    }

    @Override
    public void tick() {

    }

    public StatisticContainer getStatContainer(){
        return statContainer;
    }

    public void move(Direction dir) {
        movementHandler.move(dir);
    }

    public void takeItem(TakeableItem item) {
        inventory.add(item);
        Logger.getInstance().pushMessage("Put the "+ item.getName() + " into inventory.");
    }

    protected EquipableItem equipWeaponItem(WeaponItem weaponItem) {
        EquipableItem equippedItem = equipment.equipItem(weaponItem);
        statContainer.setWeaponDamage(equippedItem.getStatistics().getOffensiveRating().getCurrent());
        return equippedItem;
    }

    protected EquipableItem equipArmorItem(EquipableItem armorItem){
        //equip the item
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
        return equippedItem;
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
}
