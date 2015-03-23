package org.escaperun.game.model.items.equipment.weapons.smasher;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.visitors.WeaponVisitor;
import org.escaperun.game.model.items.equipment.weapons.MeleeWeapon;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class OneHandedWeapon extends MeleeWeapon {
    private final String name;
    private final String description;

    public OneHandedWeapon(Decal decal, String name, String description) {
        super(decal);
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
    protected EquipableItem equipItem(EquipmentContainer<EquipableItem> equipment, EquipableItem item){
        return null;
    }

    @Override
    public void doAction(Entity e){

    }

    @Override
    public void accept(WeaponVisitor weaponVisitor) {
        weaponVisitor.visit(this);
    }

    @Override
    public Element save(Document doc, Element parent) {
        Element superSave = super.save(doc, parent);
        superSave.setAttribute("Type", "StaffWeapon");
        superSave.setAttribute("Name", name);
        superSave.setAttribute("Description", description);
        return superSave;
    }

    @Override
    public OneHandedWeapon load(Element node) {
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

        return new OneHandedWeapon(decal, name, desc);
    }
}
