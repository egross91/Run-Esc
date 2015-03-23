package org.escaperun.game.model.stage.areaeffect;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class LevelUp extends StatisticAlteringAreaEffect{
    public LevelUp(Decal decal, Position position){
        super(decal, position);
    }

    @Override
    public void doAction(Entity e){
        Integer exp = e.getStatContainer().getLevel().getRemainingExp() + e.getStatContainer().getExperience().getCurrent();
        e.getStatContainer().getExperience().setBase(exp);
    }

    public boolean selfDestruct() {
        return true;
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("LevelUp");
        parent.appendChild(us);

        super.save(dom, us);

        return us;
    }

    @Override
    public LevelUp load(Element node) {
        LevelUp hd = new LevelUp(Decal.BLANK, new Position(0,0));
        Element elePart = (Element) node.getElementsByTagName("AreaEffect").item(0);
        hd.superLoad(elePart);
        return hd;
    }

    private void superLoad(Element ele) {
        super.load(ele);
    }
}
