package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;


public class Potion extends TakeableItem {
    public Potion(Decal decal, String name, String description) {
        super(decal);
        this.name = name;
        this.description = description;
    }
    private final String name;
    private final String description;

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
        //TODO: implement some kind of effects when entity drink potion
    }
}
