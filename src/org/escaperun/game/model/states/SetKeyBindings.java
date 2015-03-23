package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.options.LoggerOption;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;

import java.awt.*;

public class SetKeyBindings extends GameState {
    private Main previous;
    private Timer moveTimer = new Timer(6);
    private int selectedX = 0, selectedY = 0;
    private boolean action = false;
    private boolean reset = false;
    public final String KEYBINDINGS = "KEY BINDINGS";
    private final int TOP_MARGIN = 5;
    private final int OPTION_LEFT_MARGIN = GameWindow.COLUMNS/2 - KEYBINDINGS.length();
    private final int OPTION_TOP_MARGIN = TOP_MARGIN+3;
    private final int OPTION_BOTTOM_MARGIN;
    private final int MAX_KEY_LENGTH = 12;

    public SetKeyBindings(Main previous) {
        this.previous = previous;
        this.OPTION_BOTTOM_MARGIN = (KeyType.values().length-1)*2 + OPTION_TOP_MARGIN;
        setSelectionOrigin();
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        LoggerOption.getInstance().update(null, null);
        boolean exit = pressed[bindings.getBinding(KeyType.EXIT)];
        if (exit) {
            pressed[bindings.getBinding(KeyType.EXIT)] = false;
            return previous;
        }

        handleMovement(bindings, pressed);
        handleAction(bindings, pressed);
        return null;
    }

    private void handleMovement(KeyBindings bindings, boolean[] pressed) {
        if (action) return;

        boolean up = pressed[bindings.getBinding(KeyType.UP)];
        boolean down = pressed[bindings.getBinding(KeyType.DOWN)];

        int moveX = selectedX;

        if (up) moveX -= 2;
        if (down) moveX += 2;

        moveTimer.tick();
        if (moveTimer.isDone()) {
            moveX = clamp(moveX, OPTION_TOP_MARGIN, OPTION_BOTTOM_MARGIN);

            selectedX = moveX;
            moveTimer.reset();
        }
    }

    private void handleAction(KeyBindings bindings, boolean[] pressed) {
        boolean exit = pressed[bindings.getBinding(KeyType.EXIT)];
        action = (pressed[bindings.getBinding(KeyType.ACTION)] || action) ? !exit : false;

        if (action) {
            if (!reset) {
                moveTimer.reset();
                reset = true;
            }

            moveTimer.tick();

            setKey(bindings, pressed);
        }

        if (exit || moveTimer.isDone()) {
            action = false;
            reset = false;
            moveTimer.reset();
        }
    }

    private void setKey(KeyBindings bindings, boolean[] pressed) {
        int selectedIndex = (selectedX%OPTION_TOP_MARGIN)/2;
        KeyType selected = KeyType.values()[selectedIndex];

        for (KeyType key : KeyType.values()) {
            if (isSettable(key) && pressed[bindings.getBinding(key)] && !isSet(key, bindings)) {
                bindings.setBinding(selected, key.ordinal());
            }
        }
    }

    private boolean isSettable(KeyType key) {
        return !(key == KeyType.ACTION || key == KeyType.EXIT || key == null);
    }


    /**
     * RENDER STOOF
     */

    @Override
    public Decal[][] getRenderable() {
        Decal[][] window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];

        int startCol = GameWindow.COLUMNS/2 - KEYBINDINGS.length()/2;
        renderText(window, KEYBINDINGS, TOP_MARGIN, startCol);

        startCol = GameWindow.COLUMNS/2 - KEYBINDINGS.length();
        renderItemBoxes(window, OPTION_TOP_MARGIN, startCol, KeyType.values().length);


        int startRow = OPTION_TOP_MARGIN;
        startCol += 2;
        for (int i = 0; i < KeyType.values().length; ++i) {
            KeyType key = KeyType.values()[i];
            String setting = "something";

            String keyString = key.toString() + ": ";
            for (int j = 0; j < keyString.length(); ++j) {
                window[startRow][startCol+j] = new Decal(keyString.charAt(j), Color.BLACK, Color.WHITE);
            }
            for (int j = key.toString().length()+1; j < MAX_KEY_LENGTH; ++j) {
                window[startRow][startCol+j] = Decal.BLANK;
            }

            for (int j = 0; j < setting.length(); ++j) {
                window[startRow][startCol+MAX_KEY_LENGTH+j] = new Decal(setting.charAt(j), Color.BLACK, Color.WHITE);
            }

            startRow += 2;
        }

        return window;
    }

    private void renderItemBoxes(Decal[][] window, int startRow, int startCol, int numBoxes) {
        for (int i = 0; i < numBoxes; ++i) {
            if (isSelected(startRow, startCol)) {
                window[startRow][startCol] = new Decal((char)254, Color.BLACK, Color.RED);
            }
            else {
                window[startRow][startCol] = new Decal((char)254, Color.BLACK, Color.WHITE);
            }

            startRow += 2;
        }
    }

    private void renderText(Decal[][] window, String text, int startRow, int startCol) {
        for (int i = 0; i < text.length(); ++i) {
            window[startRow][startCol+i] = new Decal(text.charAt(i), Color.BLACK, Color.WHITE);
        }
    }

    private void setSelectionOrigin() {
        this.selectedX = OPTION_TOP_MARGIN;
        this.selectedY = OPTION_LEFT_MARGIN;
    }

    private int clamp(int coord, int lo, int hi) {
        if (coord < lo) return lo;
        if (coord > hi) return hi;

        return coord;
    }

    private boolean isSelected(int r, int c) {
        return (selectedX == r && selectedY == c);
    }

    private boolean isSet(KeyType key, KeyBindings bindings) {
        return (bindings.getBinding(key) != null);
    }
}
