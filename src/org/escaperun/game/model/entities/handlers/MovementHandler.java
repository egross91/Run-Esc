package org.escaperun.game.model.entities.handlers;

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

    public void move(Position p) {
        if (stage.isValid(p)) {
            entity.setPosition(p);
        }
    }
}
