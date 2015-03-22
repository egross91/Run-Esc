package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Actionable;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Touchable;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public abstract class AreaEffect implements Renderable, Touchable, Actionable {

    private Decal decal;
    private Position position;

    public AreaEffect(Decal decal, Position position) {
        this.decal = decal;
        this.position = position;
    }

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ this.decal }};
    }

    @Override
    public abstract void onTouch(Entity e);

    @Override
    public abstract void doAction(Entity e);

    public Position getPosition(){
        return position;
    }
}