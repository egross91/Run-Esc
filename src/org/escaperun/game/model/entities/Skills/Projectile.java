package org.escaperun.game.model.entities.skills;

import javafx.geometry.Pos;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;
import java.util.ArrayList;

public abstract class Projectile extends ActiveSkill {

    protected int skillDistance;      // how far will the projectile travel max of 5 atm.
    protected Direction dir;                //change to use Direction
    protected int slopeX;
    protected int slopeY;
    protected Position initialPos;
    protected Position currentPos;
    protected ArrayList<Position> affectedArea;        //holds positions of where the skill should "is"
    protected ArrayList<Position> displayArea;
    protected Decal decal;
    protected Timer moveTimer;
    protected Timer moveAmount = new Timer(0);


    public Projectile(int ofp, int dfp, int skillLevel, Entity skillOwner, int sd, Direction dir, Position start, int movesPerTick){
        super(ofp, dfp, skillLevel, skillOwner);
        skillName = "Projectile";
        this.skillLevel = 1;
        this.skillDistance = sd;
        this.moveTimer = new Timer(movesPerTick);
        this.moveAmount = new Timer(sd);
        this.dir = dir;
        this.setSlope(this.dir);

        this.initialPos = start;
        this.currentPos = start;
        this.affectedArea = new ArrayList<Position>();
    }

    // check for when we need negative values due to orientation of system
    private void setSlope(Direction dir){
        this.slopeX = dir.getDelta().x;
        this.slopeY = dir.getDelta().y;
    }

    public boolean isDone() {return moveAmount.isDone();}

    public void tick(){
        if (moveAmount.isDone()) return;
        moveTimer.tick();
        if (!moveTimer.isDone()) return;
        moveAmount.tick();
        Position newp = new Position(this.currentPos.x + this.slopeX, this.currentPos.y + this.slopeY);
        this.currentPos = newp;
        this.affectedArea.clear();
        this.affectedArea.add(newp);
        moveTimer.reset();
    }

    public ArrayList<Position> getAffectedArea(){
        return this.affectedArea;
    }

    public void setDecal(Decal d){
        this.decal = d;
    }

    public Decal[][] getRenderable(){
        return new Decal[][] {{this.decal}};
    }
}
