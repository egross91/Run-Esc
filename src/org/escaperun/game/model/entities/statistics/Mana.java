package org.escaperun.game.model.entities.statistics;

public class Mana extends DerivedStatistic<Integer> {

   private Level lvl;
   private Intellect intel;
   private Integer maxMana;
   private Integer minMana = 0;

    public Mana(Level level, Intellect intellect) {
        super(0);
        lvl = level;
        lvl.subscribe(this);
        intel = intellect;
        intel.subscribe(this);
        recalculate();
        setBase(maxMana);
    }

    @Override
    protected void recalculate() {
        maxMana = (int) (lvl.getCurrent() * 3) + (5 * intel.getCurrent());
    }

    @Override
    public Integer getBase() {
        return maxMana;
    }

    public void reduceMana(Integer amountReduced) {
        int currentMana = getCurrent();
        setBase(currentMana - amountReduced);
    }

    public void restoreMana(Integer amountRestored) {
        int currentMana = getCurrent();
        if((currentMana + amountRestored) > maxMana)
            refillMana();
        else
            setBase(currentMana + amountRestored);
    }

    public void refillMana() {
        setBase(maxMana);
    }

    @Override
    public String getName() {
        return "Mana";
    }
}
