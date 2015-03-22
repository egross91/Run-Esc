package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.handlers.StageAffectingItemHandler;
import org.escaperun.game.model.entities.skills.Projectile360Effect;
import org.escaperun.game.model.entities.skills.summoner.Bane;
import org.escaperun.game.view.Decal;

public class Bomb extends TakeableItem {
    public Bomb(Decal decal, String name, String description) {
        super(decal);
        this.name = name;
        this.description = description;
        //stageAffectingItemHandler = new StageAffectingItemHandler();
    }

    private final String name;
    private final String description;
    private StageAffectingItemHandler stageAffectingItemHandler;

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
}

