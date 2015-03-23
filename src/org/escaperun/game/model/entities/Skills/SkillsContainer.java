package org.escaperun.game.model.entities.skills;

import org.escaperun.game.model.entities.Entity;

import java.util.ArrayList;

public abstract class SkillsContainer {
    private BindWounds bindWounds;
    private Bargain bargain;
    private Observe observe;
    protected ArrayList<Skill> skills = new ArrayList<Skill>();

    public SkillsContainer(Entity skillOwner){
        bindWounds = new BindWounds();
        bargain = new Bargain();
        observe = new Observe(1, skillOwner);
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

    public abstract ArrayList<Skill> getSkillsArrayList();

}
