package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.view.Decal;

import java.util.ArrayList;

public abstract class Projectile360Effect extends Projectile {

    private static int[][] AoEPaths = {

            {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10},
            {10,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,10},
            {10,9,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,9,10},
            {10,9,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,9,10},
            {10,9,8,7,6,6,6,6,6,6,6,6,6,6,6,6,6,7,8,9,10},
            {10,9,8,7,6,5,5,5,5,5,5,5,5,5,5,5,6,7,8,9,10},
            {10,9,8,7,6,5,5,4,4,4,4,4,4,4,5,5,6,7,8,9,10},
            {10,9,8,7,6,5,4,4,3,3,3,3,3,4,4,5,6,7,8,9,10},
            {10,9,8,7,6,5,4,3,3,2,2,2,3,3,4,5,6,7,8,9,10},
            {10,9,8,7,6,5,4,3,2,1,1,1,2,3,4,5,6,7,8,9,10},
            {10,9,8,7,6,5,4,3,2,1,0,1,2,3,4,5,6,7,8,9,10},
            {10,9,8,7,6,5,4,3,2,1,1,1,2,3,4,5,6,7,8,9,10},
            {10,9,8,7,6,5,4,3,3,2,2,2,3,3,4,5,6,7,8,9,10},
            {10,9,8,7,6,5,4,4,3,3,3,3,3,4,4,5,6,7,8,9,10},
            {10,9,8,7,6,5,5,4,4,4,4,4,4,4,5,5,6,7,8,9,10},
            {10,9,8,7,6,5,5,5,5,5,5,5,5,5,5,5,6,7,8,9,10},
            {10,9,8,7,6,6,6,6,6,6,6,6,6,6,6,6,6,7,8,9,10},
            {10,9,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,9,10},
            {10,9,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,9,10},
            {10,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,10},
            {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10},
    }; //should be able to take distance up to 10

    Position arrayPos;

    public Projectile360Effect(int ofp, int dfp, int skillLevel, int sd, Direction dir, Position start, int ticksPerMove) {
        super(ofp, dfp, skillLevel, sd, dir, start, ticksPerMove);
        this.arrayPos = new Position(11,11);
        //System.out.println("initial X" + this.initialPos.x + "initial Y" + this.initialPos.y);

    }

    public void tick() {
        if (moveAmount.isDone()) return;
        moveTimer.tick();
        if (!moveTimer.isDone()) return;
        moveTimer.reset();

        this.affectedArea = new ArrayList<Position>();

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                if (AoEPaths[i][j] == (moveAmount.getTicksSince() + 1)) {
                    this.affectedArea.add(getNewPoint(i+1, j+1, arrayPos));
                }
            }
        }
        moveAmount.tick();
    }

    private Position getNewPoint(int x, int y, Position p){
        int dx,dy;
        dx = x - p.x;
        dy = y - p.y;
        return new Position(this.currentPos.x + dx, this.currentPos.y + dy);
    }

    public void setDecal(Decal d){
        this.decal = d;
    }
}
