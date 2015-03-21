package org.escaperun.game.model.entities.npc.ai;

import javafx.geometry.Pos;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.stage.Stage;


/** This AI will wander around until the avatar is in sight. When avatar is in sight, attack avatar.*/
public abstract class AggressiveAI extends AI {
    private int chaseDistance;   //TODO: get chaseDistance either from npc or
    private boolean hasSeenAvatar;

    public AggressiveAI(Stage stage, NPC npc) {
        super(stage, npc);
        hasSeenAvatar = false;
    }

    @Override
    /** Default aggressive behavior. */
    public void run() {
        movementTimer.tick();
        Position avatarPosition = stage.getAvatarPosition();
        Position initalPosition = npc.getInitialPosition();
        Position currentPosition = npc.getCurrentPosition();

        if (hasSeenAvatar) {
            int distanceToAvatar = Position.calcuateDistance(avatarPosition, currentPosition);
            //TODO: check if npc is still in max sight range.
            if (inAttackRange(distanceToAvatar)) {
                attack();
            }
            else {
                moveTowardAvatar();
            }
        }
        else {
            //TODO: Check if avatar is within range of sight. Create a "see the avatar" timer for spotting cool down.
            //Did not see avatar
            if (Position.calcuateDistance(initalPosition, currentPosition) > npc.getWanderRadius()) {
                returnHome();
            }
            else
                wander();
        }
    }

    /** Check if NPC is within attacking distance for one of its skills or weapons. */
    protected abstract boolean inAttackRange(int distance);

    /** Attack with weapon/skill that is in range of avatar */
    protected abstract void attack();
}
