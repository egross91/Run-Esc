package org.escaperun.game.model.stage.tile.areaeffect;

import org.escaperun.game.model.Touchable;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public abstract class AreaEffect implements Renderable, Touchable {
    private Decal decal;

    public AreaEffect(Decal decal) {
        this.decal = decal;
    }

    /**
     * INTERFACE IMPLEMENTATIONS
     */
    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ this.decal }};
    }

    @Override
    public abstract void onTouch(Entity e);

    @Override
    public abstract void doAction(Entity e);
}
