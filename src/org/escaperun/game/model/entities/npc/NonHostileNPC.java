package org.escaperun.game.model.entities.npc;


import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class NonHostileNPC extends NPC {

    public NonHostileNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
    }

    @Override
    public void move(Position p) {

    }

    @Override
    public void enchant() {
        //TODO: Write enchant() method to make Non-hostile NPC act a certain way if enchanted is successful.
        //Maybe need to add in parameters
    }

    @Override
    public void attack(Entity e) {
        //TODO: Implement "running away attack" or something of that nature
        //Note: Attack is in NonHostileNPC as a way to "run away" if they've been
        //attacked, e.g. a shopkeeper who just was attacked by an Avatar.
        //It can grab the entity's position, and keep running away from that position.
        /*  PSEUDOCODE
         * Position p = e.getCurrentPosition(); //Beginning of our method, grab hostile entity's coordinates
         * move((Away from p)); // Move away from that hostile entity in some (random, predetermined, etc.) fashion.
         */

    }
}
