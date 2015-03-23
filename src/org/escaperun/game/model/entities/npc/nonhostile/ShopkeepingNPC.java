package org.escaperun.game.model.entities.npc.nonhostile;

import org.escaperun.game.model.Position;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ShopkeepingNPC extends NonHostileNPC {
    public ShopkeepingNPC(Decal decal, Position initialPosition, int wanderRadius) {
        super(decal, initialPosition, wanderRadius);
    }

    @Override
    public Element save(Document dom, Element parent) {
        Element citizen = dom.createElement("ShopkeepingNPC");
        super.save(dom, citizen);
        parent.appendChild(citizen);
        return citizen;
    }

    @Override
    public ShopkeepingNPC load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("ShopkeepingNPC") != null && node.getElementsByTagName("ShopkeepingNPC").getLength() > 0)
            us = (Element) node.getElementsByTagName("ShopkeepingNPC").item(0);

        ShopkeepingNPC citizen = new ShopkeepingNPC(Decal.BLANK, new Position(0,0), 10);

        Element npcPart = (Element) us.getElementsByTagName("NPC").item(0);
        citizen.helperLoad(npcPart);
        return citizen;
    }

    private void helperLoad(Element node) {
        super.load(node);
    }
}
