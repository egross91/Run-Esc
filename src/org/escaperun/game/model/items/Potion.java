package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;


public class Potion extends TakeableItem {
    private final String name;
    private final String description;
    public Potion(Decal decal, String name, String description) {
        super(decal, name, description);
        this.name = name;
        this.description = description;
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
    public void doAction(Entity e){
        //increase hardiness and movement when avatar drink potion
        if (this.getName().equals("Elixir")) {
            e.getStatContainer().getHardiness().addDelta(-2);
            e.getStatContainer().getMovement().addDelta(-2);
            e.getStatContainer().getLife().healDamage(20);
        }
        else if (this.getName().equals("Strength Potion")) {
            e.getStatContainer().getStrength().addDelta(20);
        }
        else if (this.getName().equals("Agility Potion")) {
            e.getStatContainer().getAgility().addDelta(20);
        }
        else if (this.getName().equals("Mana Potion")) {
            e.getStatContainer().getMana().restoreMana(20);
        }

        e.getInventory().remove(this);
    }

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "Potion");
        superSave.setAttribute("Name", name);
        superSave.setAttribute("Description", description);
        return superSave;
    }

    @Override
    public Potion load(Element node) {
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

        return new Potion(decal, name, desc);
    }

    @Override
    public Decal getDecal(){
        return decal;
    }
}
