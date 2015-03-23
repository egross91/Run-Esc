package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.stage.Stage;

public class FleeingAI extends AI{
    public FleeingAI(Stage stage, NPC npc) {
        super(stage, npc);
    }

    @Override
    public void tick() {
        if (npc.isDead()) {
            onDeath();
            return;
        }
        tickTimers();

        Position avatarPosition = stage.getAvatarPosition();
        Position currentPosition = npc.getCurrentPosition();
        int dx = avatarPosition.x - currentPosition.x;
        int dy = avatarPosition.y - currentPosition.y;
        Direction dir = Direction.fromDelta(-dx, -dy);
        if (dir != null) {
            npc.move(dir);
        }
    }
}
