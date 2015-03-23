package org.escaperun.game.model.entities.npc.nonhostile;

import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;

public class ShopkeepingNPC extends NonHostileNPC {
    public ShopkeepingNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
    }
}
