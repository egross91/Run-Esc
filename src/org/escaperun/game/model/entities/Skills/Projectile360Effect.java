package org.escaperun.game.model.entities.Skills;

import javafx.geometry.Pos;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;

import java.util.ArrayList;

/**
 * Created by TubbyLumpkins on 3/19/15.
 */
public class Projectile360Effect extends Projectile{
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
    };

    Position arrayPos;

    public Projectile360Effect(int ofp, int dfp, int sd, Direction dir, Position start, Decal dec) {
        super(ofp, dfp, sd, dir, start, dec);
        this.arrayPos = new Position(5,5);

    }

    public boolean update() {
        if (this.movementTick == this.skillDistance) {
            return false;
        } else {
            this.affectedArea = new ArrayList<Position>();
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {

                }
            }
            this.upDateTick();
            return true;
        }
    }
}
