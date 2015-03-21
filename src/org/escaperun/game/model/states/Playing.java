package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;

public class Playing extends GameState {

    private Stage stage;

    public Playing(Stage stage) {
        this.stage = stage;
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
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
        return stage.getRenderable();
    }
}
