package org.escaperun.game.model.entities.Skills;

/**
 * Created by TubbyLumpkins on 3/15/15.
 */
public class Projectile extends ActiveSkills {
    private static int[][] AoEPaths = {
            {5,5,5,5,5,5,5,5,5,5,5},
            {5,5,4,4,4,4,4,4,4,5,5},
            {5,4,4,3,3,3,3,3,4,4,5},
            {5,4,3,3,2,2,2,3,3,4,5},
            {5,4,3,2,1,1,1,2,3,4,5},
            {5,4,3,2,1,0,1,2,3,4,5},
            {5,4,3,2,1,1,1,2,3,4,5},
            {5,4,3,3,2,2,2,3,3,4,5},
            {5,4,4,3,3,3,3,3,4,4,5},
            {5,5,4,4,4,4,4,4,4,5,5},
            {5,5,5,5,5,5,5,5,5,5,5}
    }; // can be used for up to range of 5
    private static int[][] LinearPaths = {
            {5,0,0,0,0,5,0,0,0,0,5},
            {0,4,0,0,0,4,0,0,0,4,0},
            {0,0,3,0,0,3,0,0,3,0,0},
            {0,0,0,2,0,2,0,2,0,0,0},
            {0,0,0,0,1,1,1,0,0,0,0},
            {5,4,3,2,1,0,1,2,3,4,5},
            {0,0,0,0,1,1,1,0,0,0,0},
            {0,0,0,2,0,2,0,2,0,0,0},
            {0,0,3,0,0,3,0,0,3,0,0},
            {0,4,0,0,0,4,0,0,0,4,0},
            {5,0,0,0,0,5,0,0,0,0,5}
    }; // can be used for up to range of 5

    //int rise = (int) Math.round(Math.sin());
    //int run = (int) Math.round(Math.cos());


    private int movementTick;       // compared to array to select distance of affected spaces.
    private int influenceRadius;    // 1 = linear : 2 = 90 degree : 3 = 360 degree all directions
    private int skillDistance;      // how far will the projectile travel max of 5 atm.
    private int Direction;

    public Projectile(int ofp, int dfp, int ir, int sd){
        super(ofp, dfp);
        movementTick = 0;

        // incorrect IR default to linear
        if(ir >= 1 || ir <= 3) {
            this.influenceRadius = ir;
        }
        else{
            this.influenceRadius = 1;
        }

        this.skillDistance = sd;


    }

    public void upDateTick(){
        movementTick++;
    }

    public boolean update(){
        if(this.influenceRadius == 1){

        }
        else{

        }
        return false;
    }

    @Override
    public String getBeneficialStat() {
        return null;
    }
}
