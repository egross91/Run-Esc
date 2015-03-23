package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ArmorRating extends DerivedStatistic<Double> implements Saveable {
    //this class is probably analagous to Life

    Hardiness hard;
    Double armorValue;


    //this statistic should be alerted when armor changes
    //that mechanism should be working..
    public ArmorRating(Hardiness hardiness, Double armorValue) {
        super(0.0); // dummy
        hard = hardiness;
        hardiness.subscribe(this);
        this.armorValue = armorValue;
        recalculate();
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement(getName());
        parent.appendChild(us);

        us.setAttribute("ArmorValue", armorValue.toString());
        return us;
    }

    @Override
    public ArmorRating load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName(getName()) != null && node.getElementsByTagName(getName()).getLength() > 0)
            us = (Element) node.getElementsByTagName(getName()).item(0);

        Double armorValue = Double.parseDouble(us.getAttribute("ArmorValue"));

        ArmorRating ret = new ArmorRating(hard, armorValue);
        return ret;
    }

    @Override
    protected void recalculate() {
        setBase((Double) (2 * hard.getCurrent() + (3 * armorValue)));
    }

    protected void setArmorValue(Double armorValue) {
        this.armorValue = armorValue;
        recalculate();
    }

    @Override
    public String getName() {
        return "ArmorRating";
    }
}
