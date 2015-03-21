package org.escaperun.game.model.entities.handlers;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.stage.Stage;

public class MovementHandler {
    private Stage stage;
    private Entity entity;

    public MovementHandler(Stage stage, Entity entity) {
        this.stage = stage;
        this.entity = entity;
    }

    public void move(Direction dir) {
        entity.setDirection(dir);

        Position currentPosition = entity.getCurrentPosition();
        Position first = new Position(currentPosition.x+dir.getDelta().x, currentPosition.y);
        if (stage.isMoveable(first)) {
            entity.setPosition(first);
        }

        Position second = new Position(currentPosition.x, currentPosition.y+dir.getDelta().y);

        if (stage.isMoveable(second)) {
            entity.setPosition(second);
        }
    }
}
