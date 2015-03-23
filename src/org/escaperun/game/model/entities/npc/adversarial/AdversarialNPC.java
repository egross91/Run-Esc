package org.escaperun.game.model.entities.npc.adversarial;

import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.Entity;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.items.equipment.weapons.smasher.FistWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.OneHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.smasher.TwoHandedWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BoomstickWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.BowWeapon;
import org.escaperun.game.model.items.equipment.weapons.sneak.ThrowingKnivesWeapon;
import org.escaperun.game.model.items.equipment.weapons.summoner.StaffWeapon;
import org.escaperun.game.view.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AdversarialNPC extends NPC {
    private int maxAttackRange;

    public AdversarialNPC(Decal decal, Position initialPosition, int wanderRadius, int maxAttackRange) {
        super(decal, initialPosition, wanderRadius, 10);
        this.maxAttackRange = maxAttackRange;
    }

    public ActiveSkill getActiveSkill(Direction dir) {return null;}


    @Override
    public Element save(Document dom, Element parent) {
        Element citizen = dom.createElement("AdversarialNPC");
        super.save(dom, citizen);
        parent.appendChild(citizen);

        citizen.setAttribute("MaxAttackRange", Integer.toString(maxAttackRange));
        return citizen;
    }

    @Override
    public AdversarialNPC load(Element node) {
        if (node == null) return null;
        Element us = node;
        if (node.getElementsByTagName("AdversarialNPC") != null && node.getElementsByTagName("AdversarialNPC").getLength() > 0)
            us = (Element) node.getElementsByTagName("AdversarialNPC").item(0);

        AdversarialNPC citizen = new AdversarialNPC(Decal.BLANK, new Position(0,0), 10, Integer.parseInt(us.getAttribute("MaxAttackRange")));
        Element npcPart = (Element) us.getElementsByTagName("NPC").item(0);
        citizen.helperLoad(npcPart);
        return citizen;
    }

    public void partialLode(Element node) {
        if (node == null) return;
        Element us = node;
        if (node.getElementsByTagName("AdversarialNPC") != null && node.getElementsByTagName("AdversarialNPC").getLength() > 0)
            us = (Element) node.getElementsByTagName("AdversarialNPC").item(0);

        Element npcPart = (Element) us.getElementsByTagName("NPC").item(0);
        helperLoad(npcPart);
    }

    private void helperLoad(Element node) {
        super.load(node);
    }

    @Override
    public void attack(Entity defender, Skill skill) {
        // TODO: Add the SuccessSkillGenerator or what have you when we have you.
    }

    @Override
    public void enchant() {
        //TODO: Write enchant() method to make Adversarial NPC act a certain way if enchanted is successful.
    }

    @Override
    public String observe() {
        return null; //TODO:
    }

    @Override
    public void visit(TwoHandedWeapon thw) {
        //TODO
    }

    @Override
    public void visit(OneHandedWeapon ohw) {
        //TODO
    }

    @Override
    public void visit(FistWeapon fw) {
        //TODO
    }

    @Override
    public void visit(BoomstickWeapon bsw) {
        //TODO
    }

    @Override
    public void visit(BowWeapon bw) {
        //TODO
    }

    @Override
    public void visit(ThrowingKnivesWeapon tkw) {
        //TODO
    }

    @Override
    public void visit(StaffWeapon sw) {
        //TODO
    }

    public int getMaxAttackRange() {
        return maxAttackRange;
    }
}
