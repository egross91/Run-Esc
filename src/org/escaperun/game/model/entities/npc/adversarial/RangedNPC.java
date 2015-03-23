package org.escaperun.game.model.entities.npc.adversarial;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.sneak.Arrow;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RangedNPC extends AdversarialNPC{
    public RangedNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius, 20);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().setBase(1);
        stats.getMovement().setBase(700);
        stats.setWeaponDamage(25.0);
        setStatContainer(stats);
    }

    @Override
    public ActiveSkill getActiveSkill(Direction dir) {
        return new Arrow(100, 0, 0, this, getMaxAttackRange(), dir, getCurrentPosition(),10);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element citizen = dom.createElement("RangedNPC");
        super.save(dom, citizen);
        parent.appendChild(citizen);
        return citizen;
    }

    @Override
    public RangedNPC load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("RangedNPC") != null && node.getElementsByTagName("RangedNPC").getLength() > 0)
            us = (Element) node.getElementsByTagName("RangedNPC").item(0);

        RangedNPC citizen = new RangedNPC(Decal.BLANK, new Position(0,0), 10);

        citizen.partialLode(us);
        return citizen;
    }

    private void helperLoad(Element node) {
        super.load(node);
    }
}
