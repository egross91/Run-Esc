package org.escaperun.game.model.options;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;
import org.escaperun.game.view.Renderable;

public class OptionContainer implements Renderable {

    public static enum ContainerType {
        CENTERED
    }

    public static final int HORIZONTAL_SPACING = 6; // in characters

    private Option[][] options;
    private ContainerType type;
    private int selectedX = 0, selectedY = 0;
    private Timer moveTimer = new Timer(10);

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
        if (transition != null) {
            return transition;
        }

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

    private Decal[][] renderCenter(Option[][] options) {
        Decal[][] ret = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];

        for (int i = 0; i < options.length; i++) {

            int x = GameWindow.ROWS / options.length - 2 + options.length * i;

            int rowSum = (options[i].length-1)*HORIZONTAL_SPACING;

            for (int j = 0; j < options[i].length; j++) {
                rowSum += options[i][j].getRenderable(selectedX == i && selectedY == j)[0].length;
            }

            int yStart = GameWindow.COLUMNS / 2 - rowSum / 2;

            for (int j = 0; j < options[i].length; j++) {
                Decal[] toblit = options[i][j].getRenderable(selectedX == i && selectedY == j)[0];
                for (int k = 0; k < toblit.length; k++) {
                    ret[x][yStart+k] = toblit[k];
                }
                yStart += toblit.length+HORIZONTAL_SPACING;
            }
        }
        return ret;
    }
}
