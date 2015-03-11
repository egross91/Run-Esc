package org.escaperun.game.model.entities.npc;

import org.escaperun.game.model.Position;

public class AdversarialNPC extends NPC {

    public AdversarialNPC(Position initialposition, int wanderRadius) {
        super(initialposition, wanderRadius);
    }

    @Override
    public void enchant() {
        //TODO: Write enchant() method to make Adversarial NPC act a certain way if enchanted is successful.
    }
}
