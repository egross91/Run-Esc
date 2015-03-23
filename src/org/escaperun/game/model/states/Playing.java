package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.options.LoggerOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.model.stage.tile.terrain.BlankTerrain;
import org.escaperun.game.model.stage.tile.terrain.GrassTerrain;
import org.escaperun.game.model.stage.tile.terrain.MountainTerrain;
import org.escaperun.game.model.stage.tile.terrain.WaterTerrain;
import org.escaperun.game.view.Decal;

import java.awt.event.KeyEvent;

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

        boolean shop = pressed[bindings.getBinding(KeyType.SHOP)];
        if(shop){
            pressed[bindings.getBinding(KeyType.SHOP)] = false;
            if (stage.nextToShop())
                return new Shop(this, stage);
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

        boolean hotKey1 = pressed[bindings.getBinding(KeyType.HOTKEY1)];
        if(hotKey1){
            pressed[bindings.getBinding(KeyType.HOTKEY1)] = false;
            stage.skillCast1();
        }

        boolean hotKey2 = pressed[bindings.getBinding(KeyType.HOTKEY2)];
        if(hotKey2) {
            pressed[bindings.getBinding(KeyType.HOTKEY2)] = false;
            stage.skillCast2();
        }

        boolean hotKey3 = pressed[bindings.getBinding(KeyType.HOTKEY3)];
        if(hotKey3) {
            pressed[bindings.getBinding(KeyType.HOTKEY3)] = false;
            stage.skillCast3();
        }

        boolean hotKey4 = pressed[bindings.getBinding(KeyType.HOTKEY4)];
        if(hotKey4) {
            pressed[bindings.getBinding(KeyType.HOTKEY4)] = false;
            stage.skillCast4();
        }

        handleMovement(bindings, pressed);

        //TODO REMOVE
        //handlePlacement(bindings, pressed);
        stage.tick();
        return null;
    }

    private void handlePlacement(KeyBindings bindings, boolean[] pressed) {
        boolean takeout = pressed[KeyEvent.VK_0];
        boolean grass = pressed[KeyEvent.VK_1];
        boolean mountain = pressed[KeyEvent.VK_2];
        boolean water = pressed[KeyEvent.VK_3];
        boolean teleport = pressed[KeyEvent.VK_4];
        boolean level = pressed[KeyEvent.VK_5];
        boolean npc1 = pressed[KeyEvent.VK_6];
        boolean npc2 = pressed[KeyEvent.VK_7];
        boolean npc3 = pressed[KeyEvent.VK_8];
        boolean npc4 = pressed[KeyEvent.VK_9];

        if (npc1) {
            pressed[KeyEvent.VK_6] = false;
            stage.addMelee();
        }

        if (npc2) {
            pressed[KeyEvent.VK_7] = false;
            stage.addRanged();
        }

        if (npc3) {
            pressed[KeyEvent.VK_8] = false;
            stage.addShop();
        }

        if (npc4) {
            pressed[KeyEvent.VK_9] = false;
            stage.addCitizen();
        }

        if (takeout) {
            stage.setTerrainAt(new BlankTerrain());
        }

        if (grass) {
            stage.setTerrainAt(new GrassTerrain());
        }

        if (mountain) {
            stage.setTerrainAt(new MountainTerrain());
        }

        if (water) {
            stage.setTerrainAt(new WaterTerrain());
        }

        if (teleport) {
            stage.addTeleport();
        }

        if (level) {
            pressed[KeyEvent.VK_5] = false;
            stage.addLevelUp();
        }
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
            for(int q = 0; q < 85; q++) {
                ret[50 - 6][q] = Decal.BLANK;
                ret[50 - (5 - i)][q] = Decal.BLANK;
            }
            for (int j = 0; j < stats[i].length; j++) {
                ret[50 - (5 - i)][(85 / 2) + j - 20] = stats[i][j];
            }
        }
        for(int x = 0; x < level.length; x++){
            ret[50 - (6)][(85/2)+ x - (level.length/2)] = level[x];
        }
        return ret;
    }

    public void runShop() {

    }
}
