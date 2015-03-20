package org.escaperun.game.model.entities.statistics;

import java.util.HashMap;
import java.util.Map;

public class DerivedStatisticContainer {
    private Map<StatEnum, Integer> derivedStatMap = new HashMap<StatEnum, Integer>();

    public DerivedStatisticContainer(){
        derivedStatMap.put(StatEnum.LEVEL, 1);
        derivedStatMap.put(StatEnum.HEALTH, 10);
        derivedStatMap.put(StatEnum.MANA, 10);
        derivedStatMap.put(StatEnum.OFFENSIVE_RATING, 1);
        derivedStatMap.put(StatEnum.DEFENSIVE_RATING, 1);
        derivedStatMap.put(StatEnum.ARMOR_RATING, 0);
    }
}
