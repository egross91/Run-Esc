package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Telescope extends InteractiveItem {
    public Telescope(Decal decal, String name, String description){super(decal, name, description);}

    @Override
    public boolean isCollidable(){return true;}

    @Override
    public void onTouch(Entity e){}

    @Override
    public void doAction(Entity e){}

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "Telescope");
        return superSave;
    }

    @Override
    public Telescope load(Element node) {
        if (node == null) return null;
        Element item;
        if (node.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        Decal decal = new Decal('0', Color.BLACK, Color.BLACK).load(item);

        return new Telescope(decal, "Telescope", "Helps you see far away");
    }

}
