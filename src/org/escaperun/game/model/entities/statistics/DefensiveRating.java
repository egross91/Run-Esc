package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DefensiveRating extends DerivedStatistic<Double> implements Saveable {

    Agility agile;
    Level lvl;
    private Double delta = 0.0;

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
        Double agileCurr = (agile.getCurrent() == null) ? 1 : agile.getCurrent();
        Integer lvlCurr = (lvl.getCurrent() == null) ? 1 : lvl.getCurrent();
        setBase((double) (3*agileCurr + 2*lvlCurr) + delta);
    }

    @Override
    public String getName() {
        return "DefRating";
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
}
