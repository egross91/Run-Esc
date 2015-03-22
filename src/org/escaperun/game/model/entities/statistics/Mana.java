package org.escaperun.game.model.entities.statistics;

public class Mana extends DerivedStatistic<Integer> {

   private Level lvl;
   private Intellect intel;
   private Integer mana;
   private Integer maxMana;
   private Double minMana = 0.0;

    public Mana(Level level, Intellect intellect) {
        super(0);
        lvl = level;
        intel = intellect;
        recalculate();
        mana = maxMana;
    }

    @Override
    protected void recalculate() {
        maxMana = (int) (lvl.getCurrent() * 2) + (3 * intel.getCurrent());
    }

    @Override
    public Integer getBase() {
        return mana;
    }

    @Override
    public Integer getCurrent() {
        return mana;
    }

    public void reduceMana(Integer amountReduced) {
        mana -= amountReduced;
    }

    public void restoreMana(Integer amountRestored) {
        mana += amountRestored;
    }

    public void refillMana() {
        mana = maxMana;
    }
}
