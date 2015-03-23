package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Actionable;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Touchable;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class AreaEffect implements Renderable, Touchable, Actionable, Saveable {

    private Decal decal;
    private Position position;

    public AreaEffect(Decal decal, Position position) {
        this.decal = decal;
        this.position = position;
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("AreaEffect");
        parent.appendChild(us);

        decal.save(dom, us);
        us.setAttribute("X", Integer.toString(position.x));
        us.setAttribute("Y", Integer.toString(position.y));

        return us;
    }

    public abstract boolean selfDestruct();

    @Override
    public AreaEffect load(Element node) {
        decal = Decal.BLANK.load(node);
        position = new Position(Integer.parseInt(node.getAttribute("X")), Integer.parseInt(node.getAttribute("Y")));
        return this;
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