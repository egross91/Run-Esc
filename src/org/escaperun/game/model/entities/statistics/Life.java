package org.escaperun.game.model.entities.statistics;


import org.escaperun.game.serialization.Saveable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Life extends DerivedStatistic<Integer> implements Saveable {

    //this class would probably be more aptly described by the term
    //"Health"
    //Life represents the amount of health left before this entity dies


    //when the entity levels up or whatever, its Life will not be increased to fill the new max value,
    //as it is coded right now...
    //unless you call refillLife()
    private Level lvl;
    private Hardiness hard;
    private Integer maxLife;
    private Integer minLife = 0;

    public Life(Level level, Hardiness hardiness) {
        super(0);
        lvl = level;
        lvl.subscribe(this);
        hard = hardiness;
        hard.subscribe(this);
        recalculate();
        setBase(maxLife); //upon initializing, give this entity full health
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement(getName());
        parent.appendChild(us);
        us.setAttribute("Base", Double.toString(getCurrent()));
        return us;
    }

    @Override
    public Life load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName(getName()) != null && node.getElementsByTagName(getName()).getLength() > 0)
            us = (Element) node.getElementsByTagName(getName()).item(0);

        Life ret = new Life(lvl, hard);

        ret.setBase(((Double)Double.parseDouble(us.getAttribute("Base"))).intValue());
        return ret;
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
        maxLife = (int) (((2*lvl.getCurrent()) + (3*hard.getCurrent())));
    }

    @Override
    public Integer getBase() {
        return maxLife;
    }

    //Entity is going to have to call this when it takes damage
    //Entity is responsible for deciding when it is dead
    public void takeDamage(int damageTaken) {
        int currentLife = getCurrent();
        setBase(currentLife - damageTaken);
    }

    public void healDamage(int damageHealed) {
        //this could be used to go over the max life.
        //to simply restore full health, use refillLife.
        int currentLife = getCurrent();

        if ((currentLife + damageHealed) > maxLife) //don't go over max health
            refillLife();
        else
            setBase(currentLife + damageHealed);
    }

    //probably need a code review to see if we actually want all this methods
    //something about 'replicated behavior'
    public void refillLife() {
         setBase(maxLife);
    }

    @Override
    public String getName() {
        return "Life";
    }
}
