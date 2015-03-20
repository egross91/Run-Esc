package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.stage.Stage;

public class DummyAI extends AI {

    @Override
    public Position getNewPosition(Stage stage, NPC e) {
        return e.getPosition();
    }
}
