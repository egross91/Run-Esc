package org.escaperun.game.model.items;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.model.items.options.ItemOption;
import org.escaperun.game.view.Decal;

public abstract class TakeableItem extends TouchableItem {
    private StatisticContainer statistics;

    public TakeableItem(Decal decal) {
        super(decal);
        this.statistics = new StatisticContainer();
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

    public ItemOption[] getOptions(final Avatar avatar) {
        final TakeableItem self = this;
        ItemOption use = new ItemOption("Use Item: ", new Runnable() {
           @Override
            public void run() {
               self.doAction(avatar);
           }
        });

        ItemOption drop = new ItemOption("Drop Item: ", new Runnable() {
            @Override
            public void run() {
                avatar.getInventory().remove(self);
            }
        });

        return new ItemOption[] { use, drop, ItemOption.NEVERMIND };
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
