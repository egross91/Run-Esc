package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class InstantDeath extends StatisticAlteringAreaEffect{
    public InstantDeath (Decal decal, Position position){
        super(decal, position);
    }

    @Override
    public void doAction(Entity e){
        e.getStatContainer().getLife().takeDamage(99999999);
    }

    public boolean selfDestruct() {
        return true;
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("InstantDeath");
        parent.appendChild(us);

        super.save(dom, us);

        return us;
    }

    @Override
    public InstantDeath load(Element node) {
        InstantDeath hd = new InstantDeath(Decal.BLANK, new Position(0,0));
        Element elePart = (Element) node.getElementsByTagName("AreaEffect").item(0);
        hd.superLoad(elePart);
        return hd;
    }

    private void superLoad(Element ele) {
        super.load(ele);
    }
}
