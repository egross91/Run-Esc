package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Strength extends PrimaryStatistic<Integer> implements Saveable {

    public Strength(){
        super(0);
        setBase(5);
    }

    private Integer armorAdd = 0;

    public void equipmentChange(EquipmentContainer<EquipableItem> equip) {
        EquipableItem eq = equip.getItemAtSlot(EquipableItem.EquipmentSlot.WEAPON.ordinal());
        armorAdd = 0;
        if (eq != null) {
            if (eq instanceof OneHandedWeapon) {
                armorAdd = 7;
            } else if (eq instanceof TwoHandedWeapon) {
                armorAdd = 15;
            }
        }
    }

    public Integer getBase() {
        return base.intValue();
    }

    public Integer getCurrent() {
        Double val = (multiplicativeDelta*(base-additiveDelta+armorAdd));
        return val.intValue();
    }

    @Override
    public String getName() {
        return "Strength";
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement(getName());
        parent.appendChild(us);

        us.setAttribute("Base", base.toString());
        us.setAttribute("AdditiveDelta", additiveDelta.toString());
        us.setAttribute("MultiplicativeDelta", multiplicativeDelta.toString());

        return us;
    }

    @Override
    public Strength load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName(getName()) != null && node.getElementsByTagName(getName()).getLength() > 0)
            us = (Element) node.getElementsByTagName(getName()).item(0);

        Double base = Double.parseDouble(us.getAttribute("Base"));
        Double additiveDelta = Double.parseDouble(us.getAttribute("AdditiveDelta"));
        Double multiplicativeDelta = Double.parseDouble(us.getAttribute("MultiplicativeDelta"));

        Strength ret = new Strength();
        ret.base = base;
        ret.additiveDelta = additiveDelta;
        ret.multiplicativeDelta = multiplicativeDelta;
        return ret;
    }
}
