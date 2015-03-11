package org.escaperun.game.model.entities;

import com.sun.org.glassfish.external.statistics.Statistic;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.items.EquipableItem;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.view.Renderable;

import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;

import java.awt.*;

public abstract class Entity implements Renderable, Tickable {

    private Position currentPosition = null;
    private final Position initialPosition;
    private final Decal decal;

    public Entity(Decal decal, Position initialPosition) {
        this.initialPosition = initialPosition;
        this.currentPosition = initialPosition;
        this.decal = decal;
    }

    @Override
    public void tick() {

    }

    public void takeItem(TakeableItem item) {
        //TODO
    }

    public void equipItem(EquipableItem item) {
        //TODO
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
        return initialPosition;
    }

    protected void setCurrentPosition(Position p){
        currentPosition = p; //Set currentPosition of this entity (only should be used with move()).
    }

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ decal }}; //Return a 1x1 decal array.
    }
}
