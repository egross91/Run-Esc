package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Mana extends DerivedStatistic<Integer> implements Saveable {

   private Level lvl;
   private Intellect intel;
   private Integer maxMana = 0;
   private Integer minMana = 0;

    public Mana(Level level, Intellect intellect) {
        super(0);
        lvl = level;
        lvl.subscribe(this);
        intel = intellect;
        intel.subscribe(this);
        recalculate();
        setBase(maxMana);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement(getName());
        parent.appendChild(us);
        us.setAttribute("Base", Double.toString(getCurrent()));
        return us;
    }

    @Override
    public Mana load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName(getName()) != null && node.getElementsByTagName(getName()).getLength() > 0)
            us = (Element) node.getElementsByTagName(getName()).item(0);

        Mana ret = new Mana(lvl, intel);

        ret.setBase(((Double)Double.parseDouble(us.getAttribute("Base"))).intValue());
        return ret;
    }

    @Override
    protected void recalculate() {
                maxMana = (int) (lvl.getCurrent() * 3) + (5 * intel.getCurrent());
                int currentMana = getBase();
                setBase(currentMana);
    }

    @Override
    public Integer getBase() {
        return maxMana;
    }

    public void reduceMana(Integer amountReduced) {
        int currentMana = getCurrent();
        setBase(currentMana - amountReduced);
    }

    public void restoreMana(Integer amountRestored) {
        int currentMana = getCurrent();
        if((currentMana + amountRestored) > maxMana)
            refillMana();
        else
            setBase(currentMana + amountRestored);
    }

    public void refillMana() {
        setBase(maxMana);
    }

    @Override
    public String getName() {
        return "Mana";
    }
}
