package org.escaperun.game.model.items;

import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public final class ObstacleItem extends Item {

    public ObstacleItem() {
        super(new Decal('B', Color.BLACK, Color.LIGHT_GRAY));
    }

    public ObstacleItem(Decal decal) {
        super(decal);
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public Decal[][] getRenderable() {
        return super.getRenderable();
    }

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "ObstacleItem");
        return superSave;
    }

    @Override
    public ObstacleItem load(Element node) {
        if (node == null) return null;
        Element item;
        if (node.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        //System.out.println("Item: " + item + " NODE: " + node);
        //System.out.println("TEST: " + item.getElementsByTagName("Decal").getLength());
        Decal decal = new Decal('0', Color.BLACK, Color.WHITE).load(item);

        return new ObstacleItem(decal);
    }
}
