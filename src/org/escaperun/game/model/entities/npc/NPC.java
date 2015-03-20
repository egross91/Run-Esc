package org.escaperun.game.model.entities.npc;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.ai.AI;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;

public abstract class NPC extends Entity {

    //Could also have a radius for wandering around for their move() function?

    private final int wanderRadius; //May need to change it from being final in order to make stuff like shopkeepers moving around whatnot after they've been attacked

    private AI ai;

    public NPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition);
        this.wanderRadius = wanderRadius;
        this.ai = null; //TODO: make
    }

    public int getWanderRadius(){
        return wanderRadius;
    }

    //TODO: Implement some functionality that's common for both types of NPCs
    //NPC needs scope (radius that they can move after a hostile entity)
    public abstract void enchant();

    protected AI getAI() {return ai;}
}
