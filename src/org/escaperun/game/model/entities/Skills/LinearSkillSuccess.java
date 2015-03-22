package org.escaperun.game.model.entities.skills;


public class LinearSkillSuccess extends SkillSuccess {
    private Skill avatarSkill;
    private Skill npcSkill;

    public LinearSkillSuccess(Skill avatarSkill, Skill npcSkill){
        this.avatarSkill = avatarSkill;
        this.npcSkill = npcSkill;
    }

}
