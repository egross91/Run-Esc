package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.AdversarialNPC;
import org.escaperun.game.model.stage.Stage;


/** This AI will wander around until the avatar is in sight. When avatar is in sight, attack avatar.*/
public abstract class AggressiveAI extends AI {
    private int chaseDistance;   //TODO: get chaseDistance either from npc or
    private boolean hasSeenAvatar;

    public AggressiveAI(Stage stage, AdversarialNPC npc) {
        super(stage, npc);
        hasSeenAvatar = false;
        chaseDistance = 50; //picked arbitrarily
    }

    @Override
    /** Default aggressive behavior. */
    public void run() {
        if (npc.isDead()) {
            onDeath();
            return;
        }

        Position avatarPosition = stage.getAvatarPosition();
        Position initialPosition = npc.getInitialPosition();
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
            if (Position.calcuateDistance(initialPosition, currentPosition) > npc.getWanderRadius()) {
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
