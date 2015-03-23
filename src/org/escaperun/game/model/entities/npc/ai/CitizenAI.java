package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.statistics.IStatSubscriber;
import org.escaperun.game.model.stage.Stage;

public class CitizenAI extends AI {


    public CitizenAI(Stage stage, NPC npc) {
        super(stage, npc);
        npc.getStatContainer().subscribeToLife(this);
    }

    @Override
    public void tick() {

    }
}
