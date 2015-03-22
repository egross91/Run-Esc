package org.escaperun.game.model.entities.statistics;

public class Mana extends DerivedStatistic {

   private Level lvl;
   private Intellect intel;
   private Double mana;
   private Double maxMana;
   private Double minMana = 0.0;

    public Mana(Level level, Intellect intellect) {
        lvl = level;
        intel = intellect;
        recalculate();
        mana = maxMana;
    }

    @Override
    protected void recalculate() {
        maxMana = (double) (lvl.getCurrent() * 2) + (3 * intel.getCurrent());
    }

    @Override
    public Double getBase() {
        return mana;
    }

    @Override
    public Double getCurrent() {
        return mana;
    }

    public void reduceMana(Double amountReduced) {
        mana -= amountReduced;
    }

    public void restoreMana(Double amountRestored) {
        mana += amountRestored;
    }

    public void refillMana() {
        mana = maxMana;
    }
}
