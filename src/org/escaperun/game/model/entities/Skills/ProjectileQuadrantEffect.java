package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.view.Decal;

import java.util.ArrayList;

/**
 * Created by TubbyLumpkins on 3/19/15.
 */
public abstract class ProjectileQuadrantEffect extends Projectile {
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

    private Position arrayPos;

    public ProjectileQuadrantEffect(int ofp, int dfp, int skillLevel, int sd, Direction dir, Position start) {
        super(ofp, dfp, skillLevel, sd, dir, start);
        this.arrayPos = new Position(11,11);
    }

    public boolean update(){
        // for the code written: Assumed that (0,0) on game board is in top left. x increase to right. y increases downward.
        // for the AoEPaths[][]: i selects row, j selects col. i-> y on board & j -> x on board

        if(movementTick == skillDistance){
            return false;
        }
        else {
            try {
                Position newp = new Position(this.currentPos.x + this.slopeX, this.currentPos.y + this.slopeY);
                Position newArrayp = new Position(this.arrayPos.x + this.slopeX, this.arrayPos.y + this.slopeY);
                if (this.movementTick == 0){        // accounts for first space.
                    this.affectedArea.add(newp);
                    this.currentPos = newp;
                    this.arrayPos = newArrayp;
                }
                else{
                    this.affectedArea = new ArrayList<Position>();
                    this.arrayPos = newArrayp;
                    this.currentPos = newp;
                    for(int i = arrayPos.y- movementTick;i <= arrayPos.y+ movementTick; i++){
                        if(i >= 0 && i < 21) {
                            for (int j = arrayPos.x - movementTick; j <= arrayPos.x + movementTick; j++) {
                                if (j >= 0 && j < 21) {
                                    if((AoEPaths[i][j] == (movementTick + 1)) && ((Math.abs(i - arrayPos.y) + Math.abs(j - arrayPos.x)) <= movementTick)){
                                        this.affectedArea.add(getNewPoint(j, i, arrayPos));
                                    }
                                }
                            }
                        }
                    }
                }
                this.upDateTick();
            }
            catch(IllegalArgumentException e){  // may be thrown from Position
                return false;
            }
            return true;
        }
    }

    private Position getNewPoint(int x, int y, Position p){
        int dx,dy;
        dx = x - p.x;
        dy = y - p.y;
        return new Position(this.currentPos.y + dx, this.currentPos.y + dy);
    }

    public void setDecal(Decal d){
        this.decal = d;
    }
}
