package org.escaperun.game.model.items.equipment.armors;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Shield extends EquipableItem {

    public Shield(Decal decal) {
        super(decal, EquipmentSlot.SHIELD, "OBBY SHIELD", "RUNESCAPE FTW");
    }

    @Override
    protected EquipableItem equipItem(EquipmentContainer<EquipableItem> equipment, EquipableItem item) {
        return equipment.equipItem(item);
    }

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "Shield");
        return superSave;
    }

    @Override
    public ChestItem load(Element node) {
        if (node == null) return null;
        Element item;
        if (node.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0)
            item = (Element) node.getElementsByTagName("Item").item(0);
        else
            item = node;
        if (item == null) return null;

        Decal decal = new Decal('0', Color.BLACK, Color.BLACK).load(item);

        return new ChestItem(decal);
    }

    @Override
    public String getName() {
        return "OBBY SHIELD";
    }

    @Override
    public String getDescription() {
        return "RUNESCAPE FTW";
    }

    @Override
    public void doAction(Entity e) {

    }
}
