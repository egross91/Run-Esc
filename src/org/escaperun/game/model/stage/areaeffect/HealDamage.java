package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class HealDamage extends StatisticAlteringAreaEffect{
    public HealDamage(Decal decal, Position position){
        super(decal, position);
    }

    @Override
    public void doAction(Entity e){
        e.getStatContainer().getLife().healDamage(20);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("HealDamage");
        parent.appendChild(us);

        super.save(dom, us);

        return us;
    }

    @Override
    public HealDamage load(Element node) {
        HealDamage hd = new HealDamage(Decal.BLANK, new Position(0,0));
        Element elePart = (Element) node.getElementsByTagName("AreaEffect").item(0);
        hd.superLoad(elePart);
        return hd;
    }

    private void superLoad(Element ele) {
        super.load(ele);
    }
}
