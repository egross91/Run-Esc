package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.Position;

import java.util.ArrayList;

public abstract class Skill {

    public abstract void incrementSkillLevel();
    public abstract String getName();
    public abstract int getSkillLevel();
}
