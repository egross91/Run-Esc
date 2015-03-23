package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class IntellectBooster extends OneShotItem {
    public IntellectBooster(Decal decal, String name, String description){super(decal, name, description);}

    @Override
    public void doAction(Entity e){
        Integer amount = e.getStatContainer().getIntellect().getBase() + 2;
        e.getStatContainer().getIntellect().setBase(amount);
    }

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "IntellectBooster");
        return superSave;
    }

    @Override
    public IntellectBooster load(Element node) {
        if (node == null) return null;
        Element item;
        if (node.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        Decal decal = new Decal('0', Color.BLACK, Color.BLACK).load(item);

        return new IntellectBooster(decal, "Intellect Booster", "Boosts Intellect");
    }
}
