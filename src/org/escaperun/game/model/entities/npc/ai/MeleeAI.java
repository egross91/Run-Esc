package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.entities.npc.adversarial.MeleeNPC;
import org.escaperun.game.model.stage.Stage;

public class MeleeAI  extends AggressiveAI{
    public MeleeAI(Stage stage, MeleeNPC npc) {
        super(stage, npc);
    }

    @Override
    protected boolean inAttackRange(int distance) {
        return distance == 1;
    }

    @Override
    protected void attack() {
        //TODO
    }
}
