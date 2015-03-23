package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.adversarial.MeleeNPC;
import org.escaperun.game.model.stage.Stage;

public class MeleeAI  extends AggressiveAI {
    protected int maxAttackRange;

    public MeleeAI(Stage stage, MeleeNPC npc) {
        super(stage, npc);
        maxAttackRange = npc.getMaxAttackRange();
    }

    @Override
    protected boolean inAttackRange(int distance) {
        return distance <= maxAttackRange;
    }

    /** Should be used only when inAttackRange is true; */
    @Override
    protected void attack() {
        Position avatarPosition = stage.getAvatarPosition();
        Position currentPosition = npc.getCurrentPosition();
        Direction dir = Direction.fromDelta(avatarPosition.x - currentPosition.x, avatarPosition.y - currentPosition.y);
        stage.addActiveSkill(((MeleeNPC)npc).getActiveSkill(dir));
    }


}
