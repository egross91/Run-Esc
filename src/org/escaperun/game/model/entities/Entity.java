package org.escaperun.game.model.entities;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponVisitor;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Renderable;

import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;

public abstract class Entity implements Renderable, Tickable, WeaponVisitor {

    private Position currentPosition = null;
    private EquipmentContainer<ArmorItem, WeaponItem> equipment;
    private final Position initialPosition;
    private final Decal decal;
    private Direction direction;

    public Entity(Decal decal, Position initialPosition) {
        this.initialPosition = initialPosition;
        this.currentPosition = initialPosition;
        this.decal = decal;
        direction = Direction.DEG_0;
    }

    public Entity(Decal decal, Position initalPosition, EquipmentContainer<ArmorItem, WeaponItem> equipment) {
        this(decal, initalPosition);
        this.equipment = equipment;
    }

    @Override
    public void tick() {

    }

    public void takeItem(TakeableItem item) {
        //TODO
    }

    public void equipItem(EquipableItem item) {
        item.equip(this);
    }

    public abstract void move(Position p);

    public void attack(Entity e){
        e.defend(this); //Our entity just "attacked" another entity, so edit their stats.
        //May need association class for this.
    }

    public void defend(Entity e){
        //TODO: Implement how an entity "defends" against an attacker, need to utilize stats in this case.
    }

    public EquipmentContainer<ArmorItem, WeaponItem> getEquipment() {
        return this.equipment;
    }

    public Position getCurrentPosition(){
        return currentPosition; //Return currentPosition of this entity
    }

    public Position getInitialPosition(){
        return initialPosition;
    }

    protected void setCurrentPosition(Position p){
        currentPosition = p; //Set currentPosition of this entity (only should be used with move()).
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected  Direction getDirection(){
        return direction;
    }

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ decal }}; //Return a 1x1 decal array.
    }
}
