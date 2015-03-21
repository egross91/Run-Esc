package org.escaperun.game.model.entities.statistics;


public class StatisticContainer {

    private Strength strength;
    private Agility agility;
    private Hardiness hardiness;
    private Intellect intellect;
    private Movement movement;
    private Experience experience;
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
        mana = new Mana(level, intellect);
        offensiveRating = new OffensiveRating(strength, level);
        defensiveRating = new DefensiveRating(agility, level);
        armorRating = new ArmorRating(hardiness);

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

}
