package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.entities.npc.adversarial.RangedNPC;
import org.escaperun.game.model.stage.Stage;

public class RangedAI extends AggressiveAI {

    public RangedAI(Stage stage, RangedNPC npc) {
        super(stage, npc);
    }

    @Override
    protected boolean inAttackRange(int distance) {
        //TODO
        return false;
    }

    @Override
    protected void attack() {
        //TODO:
    }
}
