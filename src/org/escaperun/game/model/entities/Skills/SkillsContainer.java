package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.view.Decal;

import java.awt.*;
import java.util.ArrayList;


public abstract class SkillsContainer {
    private BindWounds bindWounds;
    private Bargain bargain;
    private Observe observe;
    protected ArrayList<ActiveSkill> skills = new ArrayList<ActiveSkill>();

    public SkillsContainer(Entity skillOwner){
        bindWounds = new BindWounds();
        bargain = new Bargain();
        observe = new Observe(0, 0, 0, skillOwner, 0, Direction.EAST, new Position(0,0), 5);
        skills.add(0, bindWounds);
        skills.add(1, bargain);
        skills.add(2, observe);
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

    public abstract int getContainerSize();

    public abstract ArrayList<ActiveSkill> getSkillsArrayList();

}
