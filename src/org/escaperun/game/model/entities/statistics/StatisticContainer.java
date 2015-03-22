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
        offensiveRating = new OffensiveRating(strength, level, 0.0);
        defensiveRating = new DefensiveRating(agility, level);
        armorRating = new ArmorRating(hardiness, 0.0);
    }

    public Strength getStrength(){
        return strength;
    }

    public void setStrength(Double value){
        strength.setBase(value);
    }

    public Agility getAgility(){
        return agility;
    }

    public void setAgility(Double value){
        agility.setBase(value);
    }

    public Hardiness getHardiness(){
        return hardiness;
    }

    public void setHardiness(Double value){
        hardiness.setBase(value);
    }

    public Intellect getIntellect(){
        return intellect;
    }

    public void setIntellect(Double value){
        intellect.setBase(value);
    }

    public Movement getMovement(){
        return movement;
    }

    public void setMovement(Double value){
        movement.setBase(value);
    }

    public Experience getExperience(){
        return experience;
    }

    public void setExperience(Double value){
        experience.setBase(value);
    }

    public LivesLeft getLivesLeft(){
        return livesLeft;
    }

    public void setLivesLeft(Double value){
        livesLeft.setBase(value);
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