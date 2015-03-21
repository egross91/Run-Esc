package org.escaperun.game.model.entities.statistics;

public class Mana extends DerivedStatistic<Long> {

   private Level lvl;
   private Intellect intel;
   private long mana;
   private long maxMana;
   private long minMana = 0;

    public Mana(Level level, Intellect intellect) {
        lvl = level;
        intel = intellect;
        recalculate();
        mana = maxMana;
    }

    @Override
    protected void recalculate() {
        maxMana = (lvl.getCurrent() * 2) + (3 * intel.getCurrent());
    }

    @Override
    public Long getBase() {
        return mana;
    }

    @Override
    public Long getCurrent() {
        return mana;
    }

    public void reduceMana(long amountReduced) {
        mana -= amountReduced;
    }

    public void restoreMana(long amountRestored) {
        mana += amountRestored;
    }

    public void refillMana() {
        mana = maxMana;
    }
}
