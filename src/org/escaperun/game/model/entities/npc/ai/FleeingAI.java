package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.stage.Stage;

public class FleeingAI extends AI{
    private boolean hasSeenAvatar;

    public FleeingAI(Stage stage, NPC npc) {
        super(stage, npc);
        hasSeenAvatar = false;
    }

    @Override
    public void tick() {
        tickTimers();
        Position avatarPosition = stage.getAvatarPosition();
        Position currentPosition = npc.getCurrentPosition();
        int distanceToAvatar = Position.calcuateDistance(avatarPosition, currentPosition);
        
        if (hasSeenAvatar) {
            //Check if npc is still in max sight range.
            if (distanceToAvatar > maxSightDistance) {
                hasSeenAvatar = false;
            }
            else
                runAway();
        }
        else {
            if (distanceToAvatar < 15) { //TODO: Get spotting range from npc
               /* if (spotAvatarTimer.isDone()) {
                    spotAvatarTimer.reset();
                    //TODO: check if avatar is spotted.
                    hasSeenAvatar = true;
                }
                */
                hasSeenAvatar = true;
            }
            else
                wander();
        }
    }
}
