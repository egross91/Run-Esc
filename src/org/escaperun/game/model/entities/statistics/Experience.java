package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Experience extends PrimaryStatistic<Integer> implements Saveable {
    private Integer maxExp;

    public Experience(){
        this(0);
    }

    public Experience(Integer maxExp){
        super(0); // dummy
        setBase(0);
        this.maxExp = maxExp;
    }
    public void setMaxExp(Integer maxExp){
        this.maxExp = maxExp;
    }
    public Integer getMaxExp(){
        return maxExp;
    }

    @Override
    public String getName() {
        return "Experience";
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement(getName());
        parent.appendChild(us);

        us.setAttribute("Base", base.toString());
        us.setAttribute("AdditiveDelta", additiveDelta.toString());
        us.setAttribute("MultiplicativeDelta", multiplicativeDelta.toString());
        us.setAttribute("MaxExp", maxExp.toString());

        return us;
    }

    @Override
    public Experience load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName(getName()) != null && node.getElementsByTagName(getName()).getLength() > 0)
            us = (Element) node.getElementsByTagName(getName()).item(0);

        Double base = Double.parseDouble(us.getAttribute("Base"));
        Double additiveDelta = Double.parseDouble(us.getAttribute("AdditiveDelta"));
        Double multiplicativeDelta = Double.parseDouble(us.getAttribute("MultiplicativeDelta"));
        Integer maxExp = Integer.parseInt(us.getAttribute("MaxExp"));

        Experience ret = new Experience(maxExp);
        ret.base = base;
        ret.additiveDelta = additiveDelta;
        ret.multiplicativeDelta = multiplicativeDelta;
        return ret;
    }
}
