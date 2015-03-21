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
        int deltaX = 0;
        int deltaY = 0;

        if (stage.isMoveable(new Position(currentPosition.x+dir.getDelta().x, currentPosition.y))) {
            deltaX = dir.getDelta().x;
        }
        if (stage.isMoveable(new Position(currentPosition.x, currentPosition.y+dir.getDelta().y))) {
            deltaY = dir.getDelta().y;
        }

        entity.setPosition(new Position(currentPosition.x+deltaX, currentPosition.y+deltaY));
    }
}
