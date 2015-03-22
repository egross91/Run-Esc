package org.escaperun.game.model.entities.handlers;

import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.stage.Stage;

public class StageAffectingItemHandler {

    private TakeableItem takeableItem;
    private Stage stage;

    public StageAffectingItemHandler(Stage stage){
        this.stage = stage;
    }
}
