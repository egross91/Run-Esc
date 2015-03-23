package org.escaperun.game.model.entities.skills;

import javafx.geometry.Pos;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.events.Timer;

import java.util.ArrayList;

public abstract class PassiveSkill extends Skill {
    private static int[][] AoEPaths = {
            {11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11},
            {11,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11},
            {11,10,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,10,11},
            {11,10,9,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,9,10,11},
            {11,10,9,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,9,10,11},
            {11,10,9,8,7,6,6,6,6,6,6,6,6,6,6,6,6,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,5,5,5,5,5,5,5,5,5,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,5,4,4,4,4,4,4,4,5,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,4,4,3,3,3,3,3,4,4,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,4,3,3,2,2,2,3,3,4,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,4,3,2,1,1,1,2,3,4,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,4,3,2,1,0,1,2,3,4,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,4,3,2,1,1,1,2,3,4,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,4,3,3,2,2,2,3,3,4,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,4,4,3,3,3,3,3,4,4,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,5,4,4,4,4,4,4,4,5,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,5,5,5,5,5,5,5,5,5,5,5,6,7,8,9,10,11},
            {11,10,9,8,7,6,6,6,6,6,6,6,6,6,6,6,6,6,7,8,9,10,11},
            {11,10,9,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,9,10,11},
            {11,10,9,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,9,10,11},
            {11,10,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,10,11},
            {11,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,11},
            {11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11}
    }; //should be able to take distance up to 10

    private Position arrayPos;
    protected int skillLevel;
    private Entity skillOwner;
    protected Timer moveTimer;
    protected Timer moveAmount = new Timer(10);
    protected ArrayList<Position> affectedArea;
    private Position currentPos;
    protected String skillName;
    protected int skillDistance;

    public PassiveSkill(int skillLevel, Entity skillOwner, int movesPerTick, int skillDistance){
        this.skillLevel = skillLevel;
        this.skillOwner = skillOwner;
        this.arrayPos = new Position(11, 11);
        this.affectedArea = new ArrayList<Position>();
        this.skillDistance = skillDistance;
    }
    public void tick() {

        if(moveAmount.isDone()){
            moveAmount.reset();
        }
        affectedArea.clear();

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                if (AoEPaths[i][j] == (moveAmount.getTicksSince() + 1)) {
                    this.affectedArea.add(getNewPoint(i + 1, j + 1, arrayPos));
                }
            }
        }
        moveAmount.tick();
    }

    public void setCurrentPos(Position p){
        this.currentPos = p;
    }
    public boolean isDone() {return moveAmount.isDone();}

    private Position getNewPoint(int x, int y, Position p){
        int dx,dy;
        dx = x - p.x;
        dy = y - p.y;
        return new Position(this.currentPos.x + dx, this.currentPos.y + dy);
    }
    public ArrayList<Position> getAffectedArea(){
        return this.affectedArea;
    }

    // 2:35 3/23/15
    public abstract String doshIt(Entity e, int mov);

    public int getMoveAmount(){
        return moveAmount.getTicksSince();
    }
}

