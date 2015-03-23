package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.entities.npc.nonhostile.NonHostileNPC;
import org.escaperun.game.model.stage.Stage;

public abstract class ShopkeepingAI extends AI {
    int movementTick;

    public ShopkeepingAI(Stage stage, NonHostileNPC npc) {
        super(stage, npc);
        movementTick = 0;
    }

    @Override
    public void talk() {
        //TODO: trigger go to buy/sell state.
    }
}
