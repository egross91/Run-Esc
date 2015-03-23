package org.escaperun.game.model.items;

import org.escaperun.game.model.Collidable;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public abstract class Item implements Renderable, Collidable, Saveable {
    private Decal decal;

    public Item(Decal decal) {
        this.decal = decal;
    }

    /**
     * INTERFACE IMPLEMENTATIONS
     */
    @Override
    public abstract boolean isCollidable();

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] {{ this.decal }};
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element item = dom.createElement("Item");
        parent.appendChild(item);

        decal.save(dom, item);

        return item;
    }

    @Override
    public Item load(Element node) {
        Element item;
        if (node.getElementsByTagName("Item") != null &&
                node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        String type = item.getAttribute("Type");
        Decal decal = new Decal('0', Color.BLACK, Color.BLACK).load(item);

        if (type.equals("AgilityBooster")) {
            return new AgilityBooster(decal).load(item);
        } else if (type.equals("Bomb")) {
            return new Bomb(decal, "empty", "empty").load(item);
        } else if (type.equals("HardinessBooster")) {
            return new HardinessBooster(decal).load(item);
        } else if (type.equals("ObstacleItem")) {
            return new ObstacleItem(decal).load(item);
        } else if (type.equals("OneUp")) {
            return new OneUp(decal).load(item);
        } else if (type.equals("Potion")) {
            return new Potion(decal, "empty", "empty").load(item);
        } else if (type.equals("Puzzle")) {
            return new Puzzle(decal).load(item);
        } else if (type.equals("Quest")) {
            return new Quest(decal).load(item);
        } else if (type.equals("StrengthBooster")) {
            return new StrengthBooster(decal).load(item);
        } else if (type.equals("Telescope")) {
            return new Telescope(decal).load(item);
        } else if (type.equals("StaffWeapon")) {
            return new StaffWeapon(decal, "empty", "empty").load(item);
        } else if (type.equals("ThrowingKnivesWeapon")) {
            return new ThrowingKnivesWeapon(decal, "empty", "empty").load(item);
        } else if (type.equals("BowWeapon")) {
            return new BowWeapon(decal, "empty", "empty").load(item);
        } else if (type.equals("BoomstickWeapon")) {
            return new BoomstickWeapon(decal, "empty", "empty").load(item);
        } else if (type.equals("TwoHandedWeapon")) {
            return new TwoHandedWeapon(decal, "empty", "empty").load(item);
        } else if (type.equals("OneHandedWeapon")) {
            return new OneHandedWeapon(decal, "empty", "empty").load(item);
        } else if (type.equals("FistWeapon")) {
            return new FistWeapon(decal, "empty", "empty").load(item);
        }
        return null;
    }
}
