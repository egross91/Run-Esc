package org.escaperun.game.model.entities.statistics;

public class StatisticContainer {

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
        livesLeft = new LivesLeft();
        level = new Level(experience);
        life = new Life(level, hardiness);
        mana = new Mana(level, intellect);
        offensiveRating = new OffensiveRating(strength, level, 0);
        defensiveRating = new DefensiveRating(agility, level);
        armorRating = new ArmorRating(hardiness, 0);
    }

    public Strength getStrength(){
        return strength;
    }

    public void setStrength(Integer integer){
        strength.setBase(integer);
    }

    public Agility getAgility(){
        return agility;
    }

    public void setAgility(Integer integer){
        agility.setBase(integer);
    }

    public Hardiness getHardiness(){
        return hardiness;
    }

    public void setHardiness(Integer integer){
        hardiness.setBase(integer);
    }

    public Intellect getIntellect(){
        return intellect;
    }

    public void setIntellect(Integer integer){
        intellect.setBase(integer);
    }

    public Movement getMovement(){
        return movement;
    }

    public void setMovement(Integer integer){
        movement.setBase(integer);
    }

    public Experience getExperience(){
        return experience;
    }

    public void setExperience(Long lo){
        experience.setBase(lo);
    }

    public LivesLeft getLivesLeft(){
        return livesLeft;
    }

    public void setLivesLeft(Short sho){
        livesLeft.setBase(sho);
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

    public DefensiveRating getDefensiveRating(){
        return defensiveRating;
    }


    public ArmorRating getArmorRating(){
        return armorRating;
    }
}