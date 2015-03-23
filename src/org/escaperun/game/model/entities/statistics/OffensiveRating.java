package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OffensiveRating extends DerivedStatistic<Double> implements Saveable {

    Strength str;
    Level lvl;
    Double weaponDamage;
    private Double delta;

    //TODO
    //need a way to alert when weapon changes
    public OffensiveRating(Strength strength, Level level, Double equippedWeaponDamage) {
        super(0.0);
        str = strength;
        str.subscribe(this);
        lvl = level;
        lvl.subscribe(this);
        weaponDamage = equippedWeaponDamage;
        recalculate();
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement(getName());
        parent.appendChild(us);

        us.setAttribute("WeaponDamage", weaponDamage.toString());
        return us;
    }

    @Override
    public OffensiveRating load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName(getName()) != null && node.getElementsByTagName(getName()).getLength() > 0)
            us = (Element) node.getElementsByTagName(getName()).item(0);


        Double weaponDamage = Double.parseDouble(us.getAttribute("WeaponDamage"));

        OffensiveRating ret = new OffensiveRating(str, lvl, weaponDamage);
        return ret;
    }

    @Override
    protected void recalculate() {
        //idk I made something up
        //also need a way to notify when weapon changes
        setBase((3* str.getCurrent().doubleValue() + 2*lvl.getCurrent().doubleValue()) + weaponDamage);
    }

    protected void setWeaponDamage(Double weaponDamage) {
        this.weaponDamage = weaponDamage;
        recalculate();
    }

    @Override
    public String getName() {
        return "OffRating";
    }
}
