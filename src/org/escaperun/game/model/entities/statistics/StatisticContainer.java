package org.escaperun.game.model.entities.statistics;

import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StatisticContainer implements Renderable, Saveable {

    private Strength strength;
    private Agility agility;
    private Hardiness hardiness;
    private Intellect intellect;
    private Movement movement;
    private Experience experience;
    private LivesLeft livesLeft;
    private Level level;
    private Life life;
    private Mana mana;
    private OffensiveRating offensiveRating;
    private DefensiveRating defensiveRating;
    private ArmorRating armorRating;

    public StatisticContainer(){
        strength = new Strength();
        agility = new Agility();
        hardiness = new Hardiness();
        intellect = new Intellect();
        movement = new Movement();
        experience = new Experience();
        level = new Level(experience);
        life = new Life(level, hardiness);
        livesLeft = new LivesLeft(life);
        mana = new Mana(level, intellect);
        offensiveRating = new OffensiveRating(strength, level, 0.0);
        defensiveRating = new DefensiveRating(agility, level);
        armorRating = new ArmorRating(hardiness, 0.0);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("Statistics");
        parent.appendChild(us);

        strength.save(dom, us);
        agility.save(dom, us);
        hardiness.save(dom, us);
        intellect.save(dom, us);
        movement.save(dom, us);
        experience.save(dom, us);
        level.save(dom, us);
        life.save(dom, us);
        livesLeft.save(dom, us);
        mana.save(dom, us);
        offensiveRating.save(dom, us);
        defensiveRating.save(dom, us);
        armorRating.save(dom, us);

        return us;
    }

    @Override
    public StatisticContainer load(Element node) {
        Element us = node;
        if (node.getElementsByTagName("Statistics") != null && node.getElementsByTagName("Statistics").getLength() > 0)
            us = (Element) node.getElementsByTagName("Statistics").item(0);

        Strength s = new Strength().load(us);
        Agility a = new Agility().load(us);
        Hardiness h = new Hardiness().load(us);
        Intellect i = new Intellect().load(us);
        Movement mov = new Movement().load(us);
        Experience e = new Experience().load(us);
        Level lev = new Level(e).load(us);
        Life lif = new Life(lev, h).load(us);
        LivesLeft livesL = new LivesLeft(lif).load(us);
        Mana man = new Mana(lev, i).load(us);
        OffensiveRating off = new OffensiveRating(s, lev, 0.0).load(us);
        DefensiveRating def = new DefensiveRating(a, lev).load(us);
        ArmorRating arm = new ArmorRating(h, 0.0).load(us);
        StatisticContainer ret = new StatisticContainer();
        ret.strength = s;
        ret.agility = a;
        ret.hardiness = h;
        ret.intellect = i;
        ret.movement = mov;
        ret.experience = e;
        ret.level = lev;
        ret.life = lif;
        ret.livesLeft = livesL;
        ret.mana = man;
        ret.offensiveRating = off;
        ret.defensiveRating = def;
        ret.armorRating = arm;

        return ret;
    }

    public Strength getStrength(){
        return strength;
    }

    public Agility getAgility(){
        return agility;
    }

    public Hardiness getHardiness(){
        return hardiness;
    }

    public Intellect getIntellect(){
        return intellect;
    }

    public Movement getMovement(){
        return movement;
    }

    public Experience getExperience(){
        return experience;
    }

    public LivesLeft getLivesLeft(){
        return livesLeft;
    }

    public Level getLevel(){
        return level;
    }

    public Life getLife(){
        return life;
    }

    public Mana getMana(){
        return mana;
    }

    public OffensiveRating getOffensiveRating(){
        return offensiveRating;
    }
    public void setWeaponDamage(Double damage) {
        offensiveRating.setWeaponDamage(damage);
    }

    public DefensiveRating getDefensiveRating(){
        return defensiveRating;
    }


    public ArmorRating getArmorRating(){
        return armorRating;
    }
    public void setArmorValue(double armorValue) {
        armorRating.setArmorValue(armorValue);
    }

    @Override
    public Decal[][] getRenderable() {
        Decal[][] renderables = new Decal[12][];
        renderables[0] = renderize(getStrength());
        renderables[1] = renderize(getAgility());
        renderables[2] = renderize(getHardiness());
        renderables[3] = renderize(getIntellect());
        renderables[4] = renderize(getMovement());
        renderables[5] = renderize(getExperience());
        renderables[6] = renderize(getLivesLeft());
        renderables[7] = renderize(getLevel());
        renderables[8] = renderize(getLife());
        renderables[9] = renderize(getMana());
        renderables[10] = renderize(getOffensiveRating());
        renderables[11] = renderize(getDefensiveRating());

        return renderables;
    }

    public Decal[][] getStageRenderable(){
        Decal[][] rendrend = new Decal[6][];
        rendrend[0] = combineWithSpacing(renderizeOnStageUnary(getStrength()), renderizeOnStageBinary(getLife()), 25);
        rendrend[1] = combineWithSpacing(renderizeOnStageUnary(getAgility()), renderizeOnStageBinary(getMana()), 25);
        rendrend[2] = combineWithSpacing(renderizeOnStageUnary(getHardiness()), renderizeOnStageUnary(getOffensiveRating()), 25);
        rendrend[3] = combineWithSpacing(renderizeOnStageUnary(getIntellect()), renderizeOnStageUnary(getDefensiveRating()), 25);
        rendrend[4] = combineWithSpacing(renderizeOnStageUnary(getMovement()), renderizeOnStageUnary(getArmorRating()), 25);
        rendrend[5] = combineWithSpacing(renderizeOnStageUnary(getExperience()), renderizeOnStageUnary(getLivesLeft()), 25);
        return rendrend;
        }

    public Decal[] getLevelRender(){
        return renderizeOnStageUnary(getLevel());
    }

    public Decal[] renderize(Statistic stat) {
        String name = stat.getName() + ": ";
        String current = stat.getCurrent().toString();
        String base = stat.getBase().toString();
        String display = name + current + "/" + base;

        Decal[] ret = new Decal[display.length()];
        int index = 0;
        for (int i = 0; i < name.length(); ++i) {
            ret[index++] = new Decal(display.charAt(i), Color.BLACK, Color.WHITE);
        }
        for (int i = 0; i < current.length(); ++i) {
            ret[index++] = new Decal(current.charAt(i), Color.BLACK, Color.GREEN);
        }
        ret[index++] = new Decal('/', Color.BLACK, Color.WHITE);
        for (int i = 0; i < base.length(); ++i) {
            ret[index++] = new Decal(base.charAt(i), Color.BLACK, Color.BLUE);
        }

        return ret;
    }

    //Binary means that it has a current AND base stat. (HP, MP, etc.)
    public Decal[] renderizeOnStageBinary(Statistic stat){
        return renderize(stat);
    }

    //Unary = only the current (Strength, Lives left, etc.)
    public Decal[] renderizeOnStageUnary(Statistic stat){
        String name = stat.getName() + ": ";
        String current = stat.getCurrent().toString();
        Decal[] returned = new Decal[name.length()+current.length()];
        int index = 0;
        for (int i = 0; i < name.length(); ++i) {
            returned[index++] = new Decal(name.charAt(i), Color.BLACK, Color.WHITE);
        }
        for (int i = 0; i < current.length(); ++i) {
            returned[index++] = new Decal(current.charAt(i), Color.BLACK, Color.GREEN);
        }

        return returned;
    }

    public Decal[] combine(Decal[] first, Decal[] second) {
        Decal[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public Decal[] combineWithSpacing(Decal[] first, Decal[] second, int amtOfSpaces){
        Decal[] spaces = new Decal[amtOfSpaces];
        Arrays.fill(spaces, Decal.BLANK);
        Decal[] tempArr = combine(first, Arrays.copyOfRange(spaces, 0, amtOfSpaces - first.length));
        Decal[] returned = combine(tempArr, second);
        return returned;
    }

    public void subscribeToLivesLeft(IStatSubscriber subscriber) {
        livesLeft.subscribe(subscriber);
    }

    public void unsubcribeToLivesLeft(IStatSubscriber subscriber) {
        livesLeft.unsubscribe(subscriber);
    }
}
