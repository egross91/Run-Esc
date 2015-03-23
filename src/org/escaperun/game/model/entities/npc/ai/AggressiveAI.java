package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.adversarial.AdversarialNPC;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.stage.Stage;


/** This AI will wander around until the avatar is in sight. When avatar is in sight, attack avatar.*/
public abstract class AggressiveAI extends AI {
    private Timer spotAvatarTimer;

    public AggressiveAI(Stage stage, AdversarialNPC npc) {
        super(stage, npc);
        hasSeenAvatar = false;
        spotAvatarTimer = new Timer(60*60*5); //5 seconds
    }

    /** Stage runs AI association.
     *  Default aggressive behavior. Only attacks avatar.
     *  Currently must check if the npc is dead or not unless a on death listener is implemented.
     */
    @Override
    public void tick(){
        if (npc.isDead()) { //We could have a listener in entity instead.
            onDeath();
            return;
        }

        tickTimers();
        Position avatarPosition = stage.getAvatarPosition();
        Position initialPosition = npc.getInitialPosition();
        Position currentPosition = npc.getCurrentPosition();
        int distanceToAvatar = Position.calcuateDistance(avatarPosition, currentPosition);
        if (hasSeenAvatar) {
            //Check if npc is still in max sight range.
            if (distanceToAvatar > maxSightDistance) {
                lostTarget();
            }
            else {
                if (inAttackRange(distanceToAvatar)) {
                    attack();
                } else {
                    moveTowardAvatar();
                }
            }
        }
        else {
            if (distanceToAvatar < 10) { //TODO: Get spotting range from npc
               /* if (spotAvatarTimer.isDone()) {
                    spotAvatarTimer.reset();
                    //TODO: check if avatar is spotted.
                    hasSeenAvatar = true;
                }
                */
                spottedTarget();
            }
            if (Position.calcuateDistance(initialPosition, currentPosition) > npc.getWanderRadius()) {
                returnHome();
            } else
                wander();
        }
    }

    /** Check if NPC is within attacking distance for one of its skills or weapons. */
    protected abstract boolean inAttackRange(int distance);

    /** Attack with weapon/skill that is in range of avatar */
    protected  abstract void attack();

    protected Timer getSpotAvatarTimer() {
        return spotAvatarTimer;
    }

    protected void setSpotAvatarTimer(Timer spotAvatarTimer) {
        this.spotAvatarTimer = spotAvatarTimer;
    }

    @Override
    protected void tickTimers() {
        spotAvatarTimer.tick();
    }
}
