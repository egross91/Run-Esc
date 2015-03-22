package org.escaperun.game.model.entities.skills.summoner;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Summoner;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.*;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.statistics.DefensiveRating;
import org.escaperun.game.model.entities.statistics.Intellect;
import org.escaperun.game.view.Decal;

import java.awt.*;

public class Bane extends Projectile360Effect {

    public Bane(int ofp, int dfp, int skillLevel, int sd, Direction dir, Position start, int movesPerTick){
        super(ofp,dfp,skillLevel,sd,dir,start, movesPerTick);
        this.setDecal(new Decal('*', Color.black, Color.red));
    }

    @Override
    public LinearSkillSuccess generateSuccess(Avatar avatar, NPC npc) {
        Intellect avatarIntellect = avatar.getStatContainer().getIntellect();
        DefensiveRating npcDefensiveRating = npc.getStatContainer().getDefensiveRating();
        return null;
        //return new LinearSkillSuccess(avatarIntellect, npcDefensiveRating);
    }
}
