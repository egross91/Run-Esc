package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DefensiveRating extends DerivedStatistic<Double> implements Saveable {

    Agility agile;
    Level lvl;

    public DefensiveRating(Agility agility, Level level) {
        super(0.0); // dummy
        agile = agility;
        agile.subscribe(this);
        lvl = level;
        lvl.subscribe(this);
        recalculate();
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement(getName());
        parent.appendChild(us);

        return us;
    }

    @Override
    public DefensiveRating load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName(getName()) != null && node.getElementsByTagName(getName()).getLength() > 0)
            us = (Element) node.getElementsByTagName(getName()).item(0);

        DefensiveRating ret = new DefensiveRating(agile, lvl);
        return ret;
    }

    @Override
    protected void recalculate() {
        setBase((double) (3*agile.getCurrent() + 2*lvl.getCurrent()));
    }

    @Override
    public String getName() {
        return "DefRating";
    }
}
