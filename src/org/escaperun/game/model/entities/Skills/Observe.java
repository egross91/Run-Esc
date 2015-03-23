package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.view.Decal;

public class Observe extends PassiveSkill {

    private double STD;

    public Observe(int skillLevel, Entity skillOwner) {
        super(skillLevel, skillOwner, 3,10 );
        skillName = "Observe";
        skillLevel = 1;
    }

//    public double generateSuccess(Entity attacker, Entity defender, int moveAmount) {
  //      return 0;
   // }

    @Override
    public void incrementSkillLevel() {
        this.skillLevel++;
    }

    public String getName() {
        return skillName;
    }

    public int getSkillLevel() {
        return this.skillLevel;
    }

    public String doshIt(Entity e){

        return " ";
    }
}
