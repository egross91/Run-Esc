package org.escaperun.game.model.entities.skills;


public class BooleanSkillSuccess extends SkillSuccess {
    private boolean success;

    public void setSkillSuccess(boolean success){
        this.success = success;
    }
    public boolean getSkillSuccess(){
        return success;
    }
}
