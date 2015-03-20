package org.escaperun.game.model.entities.statistics;

import java.util.HashMap;
import java.util.Map;

public class PrimaryStatisticContainer {

    private Map<StatEnum, Integer> primaryStatMap = new HashMap<StatEnum, Integer>();

    public PrimaryStatisticContainer(){
        primaryStatMap.put(StatEnum.LIVES_LEFT, 3);
        primaryStatMap.put(StatEnum.STRENGTH, 6);
        primaryStatMap.put(StatEnum.AGILITY, 3);
        primaryStatMap.put(StatEnum.INTELLECT, 4);
        primaryStatMap.put(StatEnum.HARDINESS, 4);
        primaryStatMap.put(StatEnum.EXPERIENCE, 0);
        primaryStatMap.put(StatEnum.MOVEMENT, 5);
    }

}