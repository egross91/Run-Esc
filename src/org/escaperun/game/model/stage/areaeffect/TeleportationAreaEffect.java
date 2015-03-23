package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.controller.Sound;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

public class TeleportationAreaEffect extends AreaEffect {
    private Position teleportPosition;

    public TeleportationAreaEffect(Decal decal, Position position, Position teleportPosition) {
        super(decal, position);
        this.teleportPosition = teleportPosition;
    }

    @Override
    public void onTouch(Entity e) {
        doAction(e);
    }

    @Override
    public void doAction(Entity e) {
        Sound.TELEPORT.play();
        e.setPosition(teleportPosition);
    }

    @Override
    public Decal[][] getRenderable() {
        return super.getRenderable();
    }
}
