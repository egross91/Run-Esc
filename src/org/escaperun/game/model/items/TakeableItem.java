package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public abstract class TakeableItem extends TouchableItem {
    private StatisticContainer statistics;

    public TakeableItem(Decal decal) {
        super(decal);
    }

    public TakeableItem(Decal decal, StatisticContainer stats) {
        super(decal);
        this.statistics = stats;
    }

    public abstract String getName();

    public abstract String getDescription();

    public StatisticContainer getStatistics() {
        return this.statistics;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    /**
     * REMARKS
     * Implement the logic for doAction() in the subclasses.
     */
    @Override
    public void onTouch(Entity e) {
        e.takeItem(this);
    }
}
