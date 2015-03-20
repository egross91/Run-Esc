package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class TeleportationAreaEffect extends AreaEffect {
    private Position position;

    public TeleportationAreaEffect(Decal decal, Position pos) {
        super(decal);
        this.position = pos;
    }

    @Override
    public void onTouch(Entity e) {
        doAction(e);
    }

    @Override
    public void doAction(Entity e) {
        //TODO: When movement is changed do this
    }

    @Override
    public Decal[][] getRenderable() {
        return super.getRenderable();
    }
}
