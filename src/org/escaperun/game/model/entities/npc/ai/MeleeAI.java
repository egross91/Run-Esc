package org.escaperun.game.model.entities.npc.ai;

import javafx.geometry.Pos;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.entities.npc.adversarial.MeleeNPC;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.entities.skills.smasher.Cleave;
import org.escaperun.game.model.entities.skills.summoner.Bane;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.stage.Stage;

public class MeleeAI  extends AggressiveAI{
    protected Timer attackTimer;
    protected int maxAttackRange;

    public MeleeAI(Stage stage, MeleeNPC npc) {
        super(stage, npc);
        double temp = (npc.getStatContainer().getAgility().getCurrent()*60);    //agility per second.
        attackTimer = new Timer((int)temp);
        maxAttackRange = 1;
    }

    @Override
    protected void tickTimers() {
        super.tickTimers();
        attackTimer.tick();
    }

    @Override
    protected boolean inAttackRange(int distance) {
        return distance == maxAttackRange;
    }

    /** Must be used only when inAttackRange is true; */
    @Override
    protected void attack() {
        if (attackTimer.isDone()) {
            attackTimer.reset();
            Position avatarPosition = stage.getAvatarPosition();
            Position currentPosition = npc.getCurrentPosition();
            Direction dir = Direction.fromDelta(avatarPosition.x - currentPosition.x, avatarPosition.y - currentPosition.y);
            stage.addSkill( new Bane(1, 0, 0, npc, 10, dir, npc.getCurrentPosition(), 5));
        }
    }


}
