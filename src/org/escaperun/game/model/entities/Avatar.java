package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.skills.SkillsContainer;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;

public abstract class Avatar extends Entity {

    protected Avatar(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public StatisticContainer getAvatarStatistics(){
        return getStatContainer();
    }

    public abstract Projectile skill1();
    public abstract SkillsContainer getSkillsContainer();

    @Override
    public void talk(){
        throw new RuntimeException("Error: Avatar should never be queried to be talked to.");
    }
}
