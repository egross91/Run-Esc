package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Level extends DerivedStatistic<Integer> implements Saveable {

    Experience experience;
    Integer divisor = 100;

    public Level(Experience exp) {
        super(0);
        experience = exp;
        experience.subscribe(this);
        recalculate();
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement(getName());
        parent.appendChild(us);

        return us;
    }

    @Override
    public Level load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName(getName()) != null && node.getElementsByTagName(getName()).getLength() > 0)
            us = (Element) node.getElementsByTagName(getName()).item(0);

        Level ret = new Level(experience);
        return ret;
    }

    @Override
    protected void recalculate() {
        setBase((int) experience.getCurrent().doubleValue() / divisor);
    }

    @Override
    public String getName() {
        return "LEVEL";
    }

    //get remaining experience until next level
    public Integer getRemainingExp() {
        return (divisor - (experience.getCurrent() % divisor));
    }
}
