package org.escaperun.game.model.entities.statistics;

public enum StatEnum {
    LIVES_LEFT,
    STRENGTH,
    AGILITY,
    INTELLECT,
    HARDINESS,
    EXPERIENCE,
    MOVEMENT,
    LEVEL,
    HEALTH,
    MANA,
    OFFENSIVE_RATING,
    DEFENSIVE_RATING,
    ARMOR_RATING;

    public String toString() {
        switch(this) {
            case LIVES_LEFT:
                return "livesLeft";
            case STRENGTH:
                return "strength";
            case AGILITY:
                return "agility";
            case INTELLECT:
                return "intellect";
            case HARDINESS:
                return "hardiness";
            case EXPERIENCE:
                return "experience";
            case MOVEMENT:
                return "movement";
            case LEVEL:
                return "level";
            case HEALTH:
                return "health";
            case MANA:
                return "mana";
            case OFFENSIVE_RATING:
                return "offensiveRating";
            case DEFENSIVE_RATING:
                return "defensiveRating";
            case ARMOR_RATING:
                return "armorRating";
            default:
                return null;
        }
    }
}
