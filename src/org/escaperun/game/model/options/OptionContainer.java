package org.escaperun.game.model.options;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public class OptionContainer implements Renderable {

    public static enum ContainerType {
        CENTERED
    }

    private Option[][] options;
    private ContainerType type;
    private int selectedX = 0, selectedY = 0;
    private Timer moveTimer = new Timer(100);

    public OptionContainer(Option[][] options, ContainerType type) {
        this.options = options;
        this.type = type;
    }

    public GameState update(KeyBindings bind, boolean[] pressed) {
        boolean up = pressed[bind.getBinding(KeyType.UP)];
        boolean down = pressed[bind.getBinding(KeyType.DOWN)];
        boolean left = pressed[bind.getBinding(KeyType.LEFT)];
        boolean right = pressed[bind.getBinding(KeyType.RIGHT)];
        int curX = selectedX;
        int curY = selectedY;

        moveTimer.tick();

        if (up) curX--;
        if (down) curX++;
        if (left) curY--;
        if (right) curY++;

        if (curX != selectedX && moveTimer.isDone()) {
            if (curX >= 0 && curX < options.length) {
                selectedX = curX;
                selectedY = Math.min(selectedY, options[selectedX].length-1);
                moveTimer.reset();
            }
        }

        if (curY != selectedY && moveTimer.isDone()) {
            if (curY >= 0 && curY < options[selectedX].length) {
                selectedY = curY;
                moveTimer.reset();
            }
        }

        GameState transition = options[selectedX][selectedY].update(bind, pressed);
        if (transition != null) return transition;

        return null;
    }

    @Override
    public Decal[][] getRenderable() {
        switch (type) {
            case CENTERED:
                return renderCenter(options);
            default:
                throw new IllegalArgumentException("containertype " + type.name() + " not supported");
        }
    }

    private static Decal[][] renderCenter(Option[][] options) {
        //TODO
        return null;
    }
}
