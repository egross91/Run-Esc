package org.escaperun.game.model.items.equipment.visitors;


public interface WeaponElement {
    public void accept(WeaponVisitor weaponVisitor); //Our accept allows for the Sneak/Smasher/Summoner to find out which weapon it's actually wanting to equip.
                                                     //Each WeaponItem will have to implement this method.
}
