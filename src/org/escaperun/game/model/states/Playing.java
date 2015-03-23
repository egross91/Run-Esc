package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.options.LoggerOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;

public class Playing extends GameState {

    public Stage stage;

    public Playing(Stage stage) {
        this.stage = stage;
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        LoggerOption.getInstance().update(null, null);
        boolean escape = pressed[bindings.getBinding(KeyType.EXIT)];
        if (escape) {
            pressed[bindings.getBinding(KeyType.EXIT)] = false;
            return new Pause(this);
        }

        boolean inventory = pressed[bindings.getBinding(KeyType.INVENTORY)];
        if (inventory) {
            pressed[bindings.getBinding(KeyType.INVENTORY)] = false;
            return new Inventory(this, stage);
        }

        boolean levelUp = pressed[bindings.getBinding(KeyType.LEVEL_UP)];
        if(levelUp){
            pressed[bindings.getBinding(KeyType.LEVEL_UP)] = false;
            return new LevelUp(this, stage);
        }

        boolean interact = pressed[bindings.getBinding(KeyType.INTERACT)];
        if(interact){
            pressed[bindings.getBinding(KeyType.INTERACT)] = false;
            stage.interactionTriggered();
        }

        handleMovement(bindings, pressed);
        stage.tick();
        return null;
    }

    private void handleMovement(KeyBindings bindings, boolean[] pressed) {
        boolean up = pressed[bindings.getBinding(KeyType.UP)];
        boolean down = pressed[bindings.getBinding(KeyType.DOWN)];
        boolean left = pressed[bindings.getBinding(KeyType.LEFT)];
        boolean right = pressed[bindings.getBinding(KeyType.RIGHT)];
        boolean upright = pressed[bindings.getBinding(KeyType.UPRIGHT)];
        boolean upleft = pressed[bindings.getBinding(KeyType.UPLEFT)];
        boolean downleft = pressed[bindings.getBinding(KeyType.DOWNLEFT)];
        boolean downright = pressed[bindings.getBinding(KeyType.DOWNRIGHT)];
        boolean hotKey1 = pressed[bindings.getBinding(KeyType.HOTKEY1)];
        boolean hotKey2 = pressed[bindings.getBinding(KeyType.HOTKEY2)];
        boolean hotKey3 = pressed[bindings.getBinding(KeyType.HOTKEY3)];
        boolean interact = pressed[bindings.getBinding(KeyType.INTERACT)];

        int moveX = 0;
        int moveY = 0;
        if (up) moveX--;
        if (down) moveX++;
        if (left) moveY--;
        if (right) moveY++;
        if (upleft) {
            moveX--;
            moveY--;
        }
        if (upright) {
            moveX--;
            moveY++;
        }
        if (downleft) {
            moveX++;
            moveY--;
        }
        if (downright) {
            moveX++;
            moveY++;
        }

        if(hotKey1){
            pressed[bindings.getBinding(KeyType.HOTKEY1)] = false;
            stage.skillCast();
        }

        if(hotKey2){
            pressed[bindings.getBinding(KeyType.HOTKEY2)] = false;

        }

        if(hotKey3){
            pressed[bindings.getBinding(KeyType.HOTKEY3)] = false;
            
        }

        if(interact){
            pressed[bindings.getBinding(KeyType.INTERACT)] = false;
            stage.interactionTriggered();
        }

        moveX = clamp(moveX, -1, 1); // clamp so we dont move extra
        moveY = clamp(moveY, -1, 1); // ""

        if (moveX == 0 && moveY == 0) return; // no movement

        Direction moveDir = Direction.fromDelta(moveX, moveY);

        if (moveDir == null) return; // should not happen

        stage.moveAvatar(moveDir);
    }

    private static int clamp(int value, int lo, int hi) {
        if (value > hi) return hi;
        if (value < lo) return lo;
        return value;
    }

    @Override
    public Decal[][] getRenderable() {

        Decal[][] ret = stage.getRenderable();
        Decal[][] log = LoggerOption.getInstance().getRenderable(false);
        Decal[][] stats = stage.getAvatar().getAvatarStatistics().getStageRenderable();
        Decal[]   level = stage.getAvatar().getAvatarStatistics().getLevelRender();
        for (int i = 0; i < log.length; i++) {
            for (int j = 0; j < log[i].length; j++) {
                ret[i][j] = log[i][j];
            }
        }

        for(int i = 0; i < stats.length; i++) {
            for(int q = 0; q < stage.DEFAULT_COLUMNS; q++) {
                ret[stage.DEFAULT_ROWS - 6][q] = Decal.BLANK;
                ret[stage.DEFAULT_ROWS - (5 - i)][q] = Decal.BLANK;
            }
            for (int j = 0; j < stats[i].length; j++) {
                ret[stage.DEFAULT_ROWS - (5 - i)][(stage.DEFAULT_COLUMNS / 2) + j - 20] = stats[i][j];
            }
        }
        for(int x = 0; x < level.length; x++){
            ret[stage.DEFAULT_ROWS - (6)][(stage.DEFAULT_COLUMNS/2)+ x - (level.length/2)] = level[x];
        }
        return ret;
    }
}
