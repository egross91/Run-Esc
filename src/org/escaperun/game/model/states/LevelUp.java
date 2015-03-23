package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.entities.skills.ActiveSkill;
import org.escaperun.game.model.entities.skills.Skill;
import org.escaperun.game.model.entities.skills.SkillsContainer;
import org.escaperun.game.model.entities.statistics.Level;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.options.LoggerOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;

import java.awt.*;
import java.util.ArrayList;

public class LevelUp extends GameState {

    private final int SKILL_COLUMN = GameWindow.COLUMNS - 15;
    private final String LEVEL_UP = "LEVEL UP!";
    private final int OFFSET = 7;
    private SkillsContainer skillsContainer;
    private Timer moveTimer = new Timer(8);
    private Playing previousState;
    private Decal[][] window;
    private int skillPointsLeft = 3;
    private int selectedY = OFFSET;

    public LevelUp(Playing previousState, Stage stage){
        this.previousState = previousState;
        this.skillsContainer = stage.getAvatarSkillsContainer();
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        LoggerOption.getInstance().update(null, null);
        boolean levelUp = pressed[bindings.getBinding(KeyType.LEVEL_UP)];
        if(levelUp){
            pressed[bindings.getBinding(KeyType.LEVEL_UP)] = false;
            return previousState;
        }
        handleMovement(bindings, pressed);
        return null;
    }

    private void handleMovement(KeyBindings bindings, boolean[] pressed){
        boolean enter = pressed[bindings.getBinding(KeyType.ACTION)];
        boolean up = pressed[bindings.getBinding(KeyType.UP)];
        boolean down = pressed[bindings.getBinding(KeyType.DOWN)];

        moveTimer.tick();
        if(moveTimer.isDone()) {

            int moveY = selectedY;
            if(up && moveY > OFFSET) moveY--;
            if(down && moveY < skillsContainer.getContainerSize() + OFFSET) moveY++;
            if (enter && skillPointsLeft > 0) {
                skillPointsLeft--;
                skillsContainer.getSkillsArrayList().get(moveY - OFFSET).incrementSkillLevel();
            }
            selectedY = moveY;
            moveTimer.reset();
        }
    }

    private boolean isSelectedSkill(int row){
        return (selectedY == row);
    }

    @Override
    public Decal[][] getRenderable() {
        window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];
        renderMessage(3, LEVEL_UP );
        renderMessage(5, "You have " + skillPointsLeft + " Skill points left to upgrade Skills!" );

        String skill;
        int skillLevel;
        ArrayList<Skill> skillsArrayList = skillsContainer.getSkillsArrayList();
        for(int i = 0; i < skillsArrayList.size(); i++) {
            skill = skillsArrayList.get(i).getName();
            skillLevel = skillsArrayList.get(i).getSkillLevel();
            renderMessage(i + OFFSET, skill);
            renderSkillLevel(i + OFFSET, skillLevel);
        }

        return window;
    }

    private void renderSkillLevel(int row, int skillLevel){
        if(isSelectedSkill(row)) {
            window[row][SKILL_COLUMN] = new Decal(String.valueOf(skillLevel).charAt(0), Color.BLACK, Color.RED);
        }
        else{
            window[row][SKILL_COLUMN] = new Decal(String.valueOf(skillLevel).charAt(0), Color.BLACK, Color.WHITE);
        }
    }

    private void renderMessage(int row, String message){
        int displayIndex = (GameWindow.COLUMNS / 2) - (message.length() / 2);
        for(int i = 0; i < message.length(); i++){
            window[row][i + displayIndex] = new Decal(message.charAt(i), Color.BLACK, Color.WHITE);
        }
    }
}
