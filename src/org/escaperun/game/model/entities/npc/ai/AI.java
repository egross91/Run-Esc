package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.stage.Stage;

import java.util.Random;

/**
 * Associate the stage and the NPC, giving NPC stage behavior.
 */
public abstract class AI implements Tickable{
    protected static final Direction[] possibleDeltas = Direction.values();
    protected final Random random = new Random();
    protected final Stage stage;
    protected final NPC npc;
    protected final Timer movementTimer;
    //TODO: add dialogue class?

    public AI(Stage stage, NPC npc) {
        this.stage = stage;
        this.npc = npc;
        movementTimer = new Timer((int)(1000/npc.getMovementPoints()));
        stage.addAI(this);
    }

    public NPC getNpc() {
        return npc;
    }

    public void talk() {
        npc.talk();
    }

    /** Entitiy attempt take a random direction within it's wander radius. */
    protected void wander() {
        Position current = npc.getCurrentPosition();

        if (!movementTimer.isDone())return;
        movementTimer.reset();

        for (int attempt = 0; attempt < 4; ++attempt) {
            Direction d = possibleDeltas[random.nextInt(possibleDeltas.length)];
            Position delta = d.getDelta();
            Position candidate = new Position(current.x + delta.x, current.y + delta.y);

            if (stage.isMoveable(candidate)) {
                //Check if in wander distance
                if (npc.getWanderRadius() > Position.calcuateDistance(current, candidate))
                    npc.move(d);    //Movement timer in npc.
                return;
            }
        }
    }

    protected void returnHome() {
        //TODO: Add some pathfinding if time.
        if (movementTimer.isDone()) {
            movementTimer.reset();
            Position initalPosition = npc.getInitialPosition();
            Position currentPosition = npc.getCurrentPosition();
            int dx = initalPosition.x - currentPosition.x;
            int dy = initalPosition.y - currentPosition.y;
            Direction dir = Direction.fromDelta(dx, dy);
            if (dir != null)
                npc.move(dir);
        }
    }

    protected void moveTowardAvatar() {
        if (movementTimer.isDone()){
            movementTimer.reset();
            Position avatarPosition = stage.getAvatarPosition();
            Position currentPosition = npc.getCurrentPosition();
            int dx = avatarPosition.x - currentPosition.x;
            int dy = avatarPosition.y - currentPosition.y;
            Direction dir = Direction.fromDelta(dx, dy);
            if (dir != null)
                npc.move(dir);
        }
    }

    protected void onDeath() {
        stage.aiToRemove(this);
    }

    protected void tickTimers() {
        movementTimer.tick();
    }
}
