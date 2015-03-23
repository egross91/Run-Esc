package org.escaperun.game.model.entities.handlers;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.stage.Stage;

public class MovementHandler implements Tickable {
    private Stage stage;
    private Entity entity;
    private Timer moveTimer;

    public MovementHandler(Stage stage, Entity entity, int ticksPerMove) {
        this.stage = stage;
        this.entity = entity;
        this.moveTimer = new Timer(ticksPerMove);
    }

    public void tick() {
        moveTimer.tick();
    }

    public void move(Direction dir) {

        int move = (int)(((double)entity.getStatContainer().getMovement().getCurrent())/1000.0);
        if (move*moveTimer.getTicksSince() < moveTimer.getTicksTo()) return;
        moveTimer.reset();


        entity.setDirection(dir);
        Position currentPosition = entity.getCurrentPosition();
        int deltaX = 0;
        int deltaY = 0;


        if (stage.isMoveable(new Position(currentPosition.x+dir.getDelta().x, currentPosition.y+dir.getDelta().y))) {
            if (stage.isMoveable(new Position(currentPosition.x + dir.getDelta().x, currentPosition.y))) {
                deltaX = dir.getDelta().x;
            }
            if (stage.isMoveable(new Position(currentPosition.x, currentPosition.y + dir.getDelta().y))) {
                deltaY = dir.getDelta().y;
            }
        }

        entity.setPosition(new Position(currentPosition.x+deltaX, currentPosition.y+deltaY));
    }
}
