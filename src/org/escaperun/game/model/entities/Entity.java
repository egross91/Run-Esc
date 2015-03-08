package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.items.EquipableItem;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public abstract class Entity implements Renderable {

    private Position currentPosition = null;
    private final Position initialposition;
    private final Decal decal = null;

    protected Entity(Position initialposition) {
        this.initialposition = initialposition;
        this.currentPosition = initialposition;
    }

    public void addToInventory(TakeableItem takeable) {
        // TODO: Add Inventory and the logic for adding the item to the Inventory.
    }

    public void equipItem(EquipableItem equipable) {
        // TODO: Add Equipment and the logic for adding the item to the Equipment.
    }

    public abstract void move(Position p);

    public void attack(Entity e){
        e.defend(this); //Our entity just "attacked" another entity, so edit their stats.
        //May need association class for this.
    }

    public void defend(Entity e){
        //TODO: Implement how an entity "defends" against an attacker, need to utilize stats in this case.
    }

    public Position getCurrentPosition(){
        return currentPosition; //Return currentPosition of this entity
    }

    public Position getInitialPosition(){
        return initialposition;
    }

    protected void setCurrentPosition(Position p){
        currentPosition = p; //Set currentPosition of this entity (only should be used with move()).
    }

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ decal }}; //Return a 1x1 decal array.
    }
}
