package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class HardinessBooster extends OneShotItem {
    public HardinessBooster(Decal decal){super(decal);}


    @Override
    public void doAction(Entity e){
        e.getStatContainer().getHardiness().addDelta(1);
    }


    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "HardinessBooster");
        return superSave;
    }

    @Override
    public HardinessBooster load(Element node) {
        if (node == null) return null;
        Element item;
        if (node.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        Decal decal = new Decal('0', Color.BLACK, Color.BLACK).load(item);

        return new HardinessBooster(decal);
    }
}
