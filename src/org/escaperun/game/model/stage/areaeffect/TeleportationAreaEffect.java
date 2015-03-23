package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.controller.Sound;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("InstantDeath");
        parent.appendChild(us);
        us.setAttribute("TX", Integer.toString(teleportPosition.x));
        us.setAttribute("TY", Integer.toString(teleportPosition.y));

        super.save(dom, us);

        return us;
    }

    @Override
    public TeleportationAreaEffect load(Element node) {
        Position telepos = new Position(Integer.parseInt(node.getAttribute("TX")), Integer.parseInt(node.getAttribute("TY")));
        TeleportationAreaEffect hd = new TeleportationAreaEffect(Decal.BLANK, new Position(0,0), telepos);
        Element elePart = (Element) node.getElementsByTagName("AreaEffect").item(0);
        hd.superLoad(elePart);
        return hd;
    }

    private void superLoad(Element ele) {
        super.load(ele);
    }
}
