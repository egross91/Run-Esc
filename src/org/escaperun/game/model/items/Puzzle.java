package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Puzzle extends InteractiveItem {
    public Puzzle(Decal decal){super(decal);}

    @Override
    public boolean isCollidable(){return true;}

    @Override
    public void onTouch(Entity e){
        //TODO
    }

    @Override
    public void doAction(Entity e){
        //TODO
    }

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "Puzzle");
        return superSave;
    }

    @Override
    public Puzzle load(Element node) {
        if (node == null) return null;
        Element item;
        if (node.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        Decal decal = new Decal('0', Color.BLACK, Color.BLACK).load(item);

        return new Puzzle(decal);
    }
}
