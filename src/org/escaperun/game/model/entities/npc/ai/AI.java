package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.controller.Sound;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.statistics.IStatSubscriber;
import org.escaperun.game.model.stage.Stage;

import java.util.Random;

/**
 * Associate the stage and the NPC, giving NPC stage behavior.
 */
public abstract class AI implements Tickable, IStatSubscriber {
    protected static final Direction[] possibleDeltas = Direction.values();
    protected final Random random = new Random();
    protected final Stage stage;
    protected final NPC npc;
    protected final int maxSightDistance;
    protected int npcHealth;
    protected boolean isAttacked;
    protected boolean hasSeenAvatar;
    //TODO: add dialogue class?

    public AI(Stage stage, NPC npc) {
        this.stage = stage;
        this.npc = npc;
        stage.addAI(this);
        npcHealth = npc.getStatContainer().getLife().getCurrent();
        maxSightDistance = 20;  //Picked arbitrarily.
        isAttacked = false;
    }

    public NPC getNpc() {
        return npc;
    }

    public void talk() {
        npc.talk();
    }

    @Override
    public void notifyChange() {
        //Do not check for death here. Causes problems with projectile collusion checking.

        //Check if life
        int health = npc.getStatContainer().getLife().getCurrent();
        if (health < npcHealth){
            isAttacked = true;
        }
        npcHealth = health;
    }

    /** Entitiy attempt take a random direction within it's wander radius. */
    protected void wander() {
        Position current = npc.getCurrentPosition();

        for (int attempt = 0; attempt < 4; ++attempt) {
            Direction d = possibleDeltas[random.nextInt(possibleDeltas.length)];
            Position delta = d.getDelta();
            Position candidate = new Position(current.x + delta.x, current.y + delta.y);

            if (stage.isMoveable(candidate)) {
                //Check if in wander distance
                if (npc.getWanderRadius() > Position.calcuateDistance(candidate, npc.getInitialPosition()))
                    npc.move(d);
                return;
            }
        }
    }

    protected void returnHome() {
        //TODO: Add some pathfinding if time.
        Position initalPosition = npc.getInitialPosition();
        Position currentPosition = npc.getCurrentPosition();
        int dx = initalPosition.x - currentPosition.x;
        int dy = initalPosition.y - currentPosition.y;
        Direction dir = Direction.fromDelta(dx, dy);
        if (dir != null)
            npc.move(dir);
    }

    protected void moveTowardAvatar() {
        Position avatarPosition = stage.getAvatarPosition();
        Position currentPosition = npc.getCurrentPosition();
        int dx = avatarPosition.x - currentPosition.x;
        int dy = avatarPosition.y - currentPosition.y;
        Direction dir = Direction.fromDelta(dx, dy);
        if (dir != null)
            npc.move(dir);
    }

    protected void onDeath() {
        Logger.getInstance().pushMessage("Tis just a flesh wound...");
        stage.aiToRemove(this);
        Sound.CREEPDEAD.play();
    }

    protected void lostTarget() {
        hasSeenAvatar = false;
        isAttacked = false;
    }

    protected void spottedTarget() {
        hasSeenAvatar = true;
    }

    protected void runAway() {

    }

    protected void tickTimers() {}


}
