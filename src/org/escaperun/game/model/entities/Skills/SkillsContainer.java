package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;

import java.awt.*;


public abstract class SkillsContainer {
    private BindWounds bindWounds;
    private Bargain bargain;
    private Observe observe;

    public SkillsContainer(){
        bindWounds = new BindWounds();
        bargain = new Bargain();
        observe = new Observe(0, 0, 0, Direction.EAST, new Position(0,0));
    }

    public BindWounds getBindWounds(){
        return bindWounds;
    }

    public Bargain getBargain(){
        return bargain;
    }

    public Observe getObserve(){
        return observe;
    }
}
