package org.escaperun.game.model.entities.npc.adversarial;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.OneHandedAttack;
import org.escaperun.game.model.entities.skills.smasher.Cleave;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MeleeNPC extends AdversarialNPC {

    public MeleeNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius, 1);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().setBase(1);
        stats.getMovement().setBase(500);
        stats.setWeaponDamage(100.0);
        setStatContainer(stats);
    }

    @Override
    public ActiveSkill getActiveSkill(Direction dir) {
        double temp = getStatContainer().getOffensiveRating().getCurrent();
        return new Cleave((int)temp, 0, 0, this, getMaxAttackRange() + 1, dir, getCurrentPosition(), 2);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element citizen = dom.createElement("MeleeNPC");
        super.save(dom, citizen);
        parent.appendChild(citizen);
        return citizen;
    }

    @Override
    public MeleeNPC load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("MeleeNPC") != null && node.getElementsByTagName("MeleeNPC").getLength() > 0)
            us = (Element) node.getElementsByTagName("MeleeNPC").item(0);

        MeleeNPC citizen = new MeleeNPC(Decal.BLANK, new Position(0,0), 10);

        citizen.partialLode(us);
        return citizen;
    }

    private void helperLoad(Element node) {
        super.load(node);
    }
}
