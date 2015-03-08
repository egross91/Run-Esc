package org.escaperun.game.model.entities.npc;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;

public abstract class NPC extends Entity {

    //TODO: Implement some functionality that's common for both types of NPCs
    //NPC needs scope (radius that they can move after a hostile entity)
    //Could also have a radius for wandering around for their move() function?

    private final int wanderRadius; //May need to change it from being final in order to make stuff like shopkeepers moving around whatnot after they've been attacked

    public NPC(Position initialposition, int wanderRadius) {
        super(initialposition);
        this.wanderRadius = wanderRadius;
    }

    public int getWanderRadius(){
        return wanderRadius;
    }

    @Override
    public void move(Position p){
        //TODO: Implement a move() function that will make sure the NPC does not wander outside of its wander radius.

    }

}
