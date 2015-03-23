package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.adversarial.RangedNPC;
import org.escaperun.game.model.stage.Stage;

public class RangedAI extends AggressiveAI {
    protected int maxAttackRange;

    public RangedAI(Stage stage, RangedNPC npc) {
        super(stage, npc);
        maxAttackRange = npc.getMaxAttackRange();
    }

    @Override
    protected boolean inAttackRange(int distance) {
        return maxAttackRange >= distance;
    }

    @Override
    protected void attack() {
        Position avatarPosition = stage.getAvatarPosition();
        Position currentPosition = npc.getCurrentPosition();
        Direction dir = Direction.fromDelta(avatarPosition.x - currentPosition.x, avatarPosition.y - currentPosition.y);
        stage.addActiveSkill(((RangedNPC)npc).getActiveSkill(dir));
    }
}
