package org.escaperun.game.model.entities.skills;

import javafx.geometry.Pos;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;
import java.util.ArrayList;

public abstract class Projectile extends ActiveSkill {

    protected int movementTick;       // compared to array to select distance of affected spaces.
    protected int skillDistance;      // how far will the projectile travel max of 5 atm.
    protected Direction dir;                //change to use Direction
    protected int slopeX;
    protected int slopeY;
    protected Position initialPos;
    protected Position currentPos;
    protected Position dispPos;
    protected ArrayList<Position> affectedArea;        //holds positions of where the skill should "is"
    protected ArrayList<Position> displayArea;
    protected Decal decal;

    public Projectile(int ofp, int dfp, int sd, Direction dir, Position start){
        super(ofp, dfp);
        this.movementTick = 0;
        this.skillDistance = sd;

        this.dir = dir;
        this.setSlope(this.dir);
        
        this.initialPos = start;
        this.dispPos = new Position(25,42);
        this.currentPos = start;
        this.affectedArea = new ArrayList<Position>();
    }

    // check for when we need negative values due to orientation of system
    private void setSlope(Direction dir){
        this.slopeX = dir.getDelta().x;
        this.slopeY = dir.getDelta().y;
    }

    public void upDateTick(){
        movementTick++;
    }

    public boolean update(){
        if(movementTick == skillDistance){
            return false;
        }
        else {
            try {
                Position newp = new Position(this.currentPos.x + this.slopeX, this.currentPos.y + this.slopeY);
                this.currentPos = newp;
                this.affectedArea = new ArrayList<Position>();
                this.affectedArea.add(newp);


                //test
                Position dispP = new Position(this.dispPos.x + this.slopeX, this.dispPos.y + this.slopeY);
                this.dispPos = dispP;
                this.displayArea = new ArrayList<Position>();
                this.displayArea.add(dispP);
                //endtest

                this.upDateTick();
            }
            catch(IllegalArgumentException e){  // may be thrown from Position
                return false;
            }
            return true;
        }
    }

    public int getSlopeX(){
        return slopeX;
    }

    public int getSlopeY(){
        return slopeY;
    }

    public ArrayList<Position> getAffectedArea(){
        return this.affectedArea;
    }

    public void setDecal(Decal d){
        this.decal = d;
    }

    public int getMovementTick(){
        return movementTick;
    }

    public Decal[][] getRenderable(){
        return new Decal[][] {{this.decal}};
    }

    public ArrayList<Position> getDisplayArea(){return this.displayArea;}
}
