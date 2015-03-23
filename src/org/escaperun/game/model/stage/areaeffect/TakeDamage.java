package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TakeDamage extends StatisticAlteringAreaEffect {
    public TakeDamage(Decal decal, Position position){
        super(decal, position);
    }

    @Override
    public void doAction(Entity e){
        e.getStatContainer().getLife().takeDamage(20);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("TakeDamage");
        parent.appendChild(us);

        super.save(dom, us);

        return us;
    }

    @Override
    public TakeDamage load(Element node) {
        TakeDamage hd = new TakeDamage(Decal.BLANK, new Position(0,0));
        Element elePart = (Element) node.getElementsByTagName("AreaEffect").item(0);
        hd.superLoad(elePart);
        return hd;
    }

    private void superLoad(Element ele) {
        super.load(ele);
    }
}
