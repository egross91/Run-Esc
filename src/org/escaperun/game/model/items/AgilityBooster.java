package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class AgilityBooster extends OneShotItem {

    public AgilityBooster(Decal decal){super(decal);}

    @Override
    public void doAction(Entity e){
        Double amount = e.getStatContainer().getAgility().getBase() + 2.0;
        e.getStatContainer().getAgility().setBase(amount);
    }

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "AgilityBooster");
        return superSave;
    }

    @Override
    public AgilityBooster load(Element node) {
        if (node == null) return null;
        Element item;
        if (node.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        Decal decal = new Decal('0', Color.BLACK, Color.BLACK).load(item);

        return new AgilityBooster(decal);
    }
}
