package org.escaperun.game.model.entities.statistics;


public class Life extends DerivedStatistic<Integer> {

    //this class would probably be more aptly described by the term
    //"Health"
    //Life represents the amount of health left before this entity dies


    private Level lvl;
    private Hardiness hard;
    private Integer life;
    private Integer maxLife;
    private Double minLife = 0.0;

    public Life(Level level, Hardiness hardiness) {
        super(0);
        lvl = level;
        hard = hardiness;
        recalculate();
        life = maxLife; //upon initializing, give this entity full health
    }

    //recalculate here is NOT THE SAME as taking damage.
    //this method would recalulate how much max life the entity has
    //for example, life would be recalulated upon a level up
    //thus changing the max life.
    //also a change to Level or Hardiness will not change the current life the entity has
    //only the max life
    @Override
    protected void recalculate() {
        //made this up right here on the spot
        maxLife = (int) ((lvl.getCurrent() * 2) + (3 * hard.getCurrent()));
    }

    @Override
    public Integer getBase() {
        return maxLife;
    }

    @Override
    public Integer getCurrent() {
        return life;
    }

    //Entity is going to have to call this when it takes damage
    //Entity is responsible for deciding when it is dead
    public void takeDamage(int damageTaken) {
        life -= damageTaken;
    }

    public void healDamage(int damageHealed) {
        //this could be used to go over the max life.
        //to simply restore full health, use refillLife.
        life += damageHealed;
    }

    //probably need a code review to see if we actually want all this methods
    //something about 'replicated behavior'
    public void refillLife() {
        life = maxLife;
    }
}
