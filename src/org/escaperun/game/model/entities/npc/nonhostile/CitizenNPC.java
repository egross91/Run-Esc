package org.escaperun.game.model.entities.npc.nonhostile;


import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.skills.SmasherSkillsContainer;
import org.escaperun.game.model.entities.statistics.StatisticContainer;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CitizenNPC extends NonHostileNPC {
    public CitizenNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
        StatisticContainer stats = new StatisticContainer();
        stats.getLivesLeft().setBase(1);
        stats.getHardiness().setBase(1);
        stats.getExperience().setBase(0);
        stats.getMovement().setBase(500);
        setStatContainer(stats);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element citizen = dom.createElement("CitizenNPC");
        super.save(dom, citizen);
        parent.appendChild(citizen);
        return citizen;
    }

    @Override
    public CitizenNPC load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("CitizenNPC") != null && node.getElementsByTagName("CitizenNPC").getLength() > 0)
            us = (Element) node.getElementsByTagName("CitizenNPC").item(0);

        CitizenNPC citizen = new CitizenNPC(Decal.BLANK, new Position(0,0), 10);

        Element npcPart = (Element) us.getElementsByTagName("NPC").item(0);
        citizen.helperLoad(npcPart);
        return citizen;
    }

    private void helperLoad(Element node) {
        super.load(node);
    }
}
