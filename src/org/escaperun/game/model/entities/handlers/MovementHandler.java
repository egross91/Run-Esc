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

        double move = ((((double)entity.getStatContainer().getMovement().getCurrent())/1000.0)*moveTimer.getTicksSince());
        if (move < moveTimer.getTicksTo()) return;
        moveTimer.reset();

        entity.setDirection(dir);

        Position curPos = entity.getCurrentPosition();
        if (stage.isMoveable(new Position(curPos.x+dir.getDelta().x, curPos.y))) {
            entity.setPosition(new Position(curPos.x+dir.getDelta().x, curPos.y));
        }

        curPos = entity.getCurrentPosition();
        if (stage.isMoveable(new Position(curPos.x, curPos.y+dir.getDelta().y))) {
            entity.setPosition(new Position(curPos.x, curPos.y+dir.getDelta().y));
        }
    }
}
