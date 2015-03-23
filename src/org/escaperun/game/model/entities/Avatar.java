package org.escaperun.game.model.entities;

import org.escaperun.game.controller.Sound;
import org.escaperun.game.model.Position;
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

    public abstract Projectile skill1();
    public abstract SkillsContainer getSkillsContainer();

    public void gainXP(double amount){
        this.getStatContainer().getExperience().setBase((int)(this.getStatContainer().getExperience().getBase() + amount));
    }
    @Override
    public void talk(){
        throw new RuntimeException("Error: Avatar should never be queried to be talked to.");
    }

    public boolean attemptSkillCast() { //a spell will only be cast if the avatar has enough mana
        //TODO: need to implement mana restoration over time
        int temp_manaRemaining = this.getStatContainer().getMana().getCurrent() - this.skill1().getManaCost();
        if(temp_manaRemaining >= 0) { //casting the spell is OK
            playAttackSound();
            this.getStatContainer().getMana().reduceMana(this.skill1().getManaCost());
            return true;
        }else
            return false;
    }

    public abstract void playAttackSound();
}
