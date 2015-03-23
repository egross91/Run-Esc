package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.handlers.StageAffectingItemHandler;
import org.escaperun.game.model.entities.skills.Projectile360Effect;
import org.escaperun.game.model.entities.skills.summoner.Bane;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Bomb extends TakeableItem {
    private final String name;
    private final String description;
    private StageAffectingItemHandler stageAffectingItemHandler;
    public Bomb(Decal decal, String name, String description) {
        super(decal, name, description);
        this.name = name;
        this.description = description;
        //stageAffectingItemHandler = new StageAffectingItemHandler();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void doAction(Entity e) {
        //stageAffectingItemHandler.applyToMap();
    }

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "Bomb");
        superSave.setAttribute("Name", name);
        superSave.setAttribute("Description", description);
        return superSave;
    }

    @Override
    public Bomb load(Element node) {
        if (node == null) return null;
        Element item;
        if (node.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        Decal decal = new Decal('0', Color.BLACK, Color.BLACK).load(item);
        String name = item.getAttribute("Name");
        String desc = item.getAttribute("Description");

        return new Bomb(decal, name, desc);
    }
}

