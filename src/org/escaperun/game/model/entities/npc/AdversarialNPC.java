package org.escaperun.game.model.entities.npc;

import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;

public class AdversarialNPC extends NPC {

    public AdversarialNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
    }

    @Override
    public void enchant() {
        //TODO: Write enchant() method to make Adversarial NPC act a certain way if enchanted is successful.
    }
}
