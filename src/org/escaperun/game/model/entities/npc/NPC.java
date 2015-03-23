package org.escaperun.game.model.entities.npc;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.ai.AI;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class NPC extends Entity implements Saveable {

    //Could also have a radius for wandering around for their move() function?

    private int wanderRadius; //May need to change it from being final in order to make stuff like shopkeepers moving around whatnot after they've been attacked
    private int XPworth;

    public NPC(Decal decal, Position initialPosition, int wanderRadius, int XP) {
        super(decal, initialPosition);
        this.wanderRadius = wanderRadius;
        this.XPworth = XP;
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element us = dom.createElement("NPC");
        parent.appendChild(us);

        super.save(dom, us);

        us.setAttribute("WanderRadius", Integer.toString(wanderRadius));
        us.setAttribute("XPWorth", Integer.toString(XPworth));
        return us;
    }

    @Override
    public NPC load(Element node) {
        if (node == null) return null;
        Element entityPart = (Element) node.getElementsByTagName("Entity").item(0);
        superLoad(entityPart);
        wanderRadius = Integer.parseInt(node.getAttribute("WanderRadius"));
        XPworth = Integer.parseInt(node.getAttribute("XPWorth"));
        return this;
    }

    private void superLoad(Element entityPart) {
        super.load(entityPart);
    }

    public int getWanderRadius(){
        return wanderRadius;
    }

    //TODO: Implement some functionality that's common for both types of NPCs
    //NPC needs scope (radius that they can move after a hostile entity)
    public abstract void enchant();

    public abstract String observe();

    @Override
    public void talk(){
        Logger.getInstance().pushMessage("This worked!!");
    }

    public int getXPworth(){
        return this.XPworth;
    }
}
