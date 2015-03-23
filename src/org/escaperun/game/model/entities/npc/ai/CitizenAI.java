package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.nonhostile.CitizenNPC;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.stage.Stage;

public class CitizenAI extends AI {
    Timer wanderTimer;
    private boolean hateAvatar;

    public CitizenAI(Stage stage, CitizenNPC npc) {
        super(stage, npc);
        npc.getStatContainer().subscribeToLife(this);
        wanderTimer = new Timer(120);
        hateAvatar = false;

    }

    @Override
    public void tick() {
        if (npc.isDead()) { //We could have a listener in entity instead.
            onDeath();
            return;
        }

        tickTimers();
        Position avatarPosition = stage.getAvatarPosition();
        Position currentPosition = npc.getCurrentPosition();
        int distanceToAvatar = Position.calcuateDistance(avatarPosition, currentPosition);
        if (isAttacked) {
            hateAvatar = true;
            runAway();
            if (distanceToAvatar > maxSightDistance){
                lostTarget();
            }
        }
        if (hateAvatar) {
            if (hasSeenAvatar) {
                if (distanceToAvatar > maxSightDistance) {
                    lostTarget();
                }
                runAway();
            }
            else {
                if (distanceToAvatar < 15) {    //TODO: get spotting range from stats
                    spottedTarget();
                }
                if (wanderTimer.isDone()) {
                    wanderTimer.reset();
                    wander();
                }
            }
        }
        else
            if (wanderTimer.isDone()) {
                wanderTimer.reset();
                wander();
            }
    }
}
