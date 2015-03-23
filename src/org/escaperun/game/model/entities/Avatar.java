package org.escaperun.game.model.entities;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.entities.skills.SkillsContainer;
import org.escaperun.game.model.entities.statistics.IStatSubscriber;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;

public abstract class Avatar extends Entity {

    protected Avatar(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public StatisticContainer getAvatarStatistics(){
        return getStatContainer();
    }

    public abstract SkillsContainer getSkillsContainer();

    public void gainXP(double amount){
        this.getStatContainer().getExperience().setBase((int)(this.getStatContainer().getExperience().getBase() + amount));
    }
    @Override
    public void talk(){
        throw new RuntimeException("Error: Avatar should never be queried to be talked to.");
    }

    protected int getManaRemaining() {
        return this.getStatContainer().getMana().getCurrent();
    }

    public abstract ActiveSkill attemptSkillCast1(Logger log);
   /* public abstract ActiveSkill attemptSkillCast2(Logger log);
    public abstract ActiveSkill attemptSkillCast3(Logger log);
    public abstract ActiveSkill attemptSkillCast4(Logger log);*/

    public abstract void playAttackSound();
}
