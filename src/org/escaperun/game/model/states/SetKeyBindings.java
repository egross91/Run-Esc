package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.options.LoggerOption;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SetKeyBindings extends GameState {
    private Main previous;
    private Timer moveTimer = new Timer(6);
    private int selectedX = 0, selectedY = 0;
    private KeyBindings defaultKeyBindings = new KeyBindings();
    private KeyBindings alteredKeyBindings = new KeyBindings();
    private boolean isSet = false;
    private boolean action = false;
    private boolean reset = false;
    public final String KEYBINDINGS = "KEY BINDINGS";
    public final String DEFAULT = "DEFAULT";
    private final int TOP_MARGIN = 5;
    private final int OPTION_LEFT_MARGIN = GameWindow.COLUMNS/2 - KEYBINDINGS.length();
    private final int OPTION_TOP_MARGIN = TOP_MARGIN+3;
    private final int OPTION_BOTTOM_MARGIN;
    private final int MAX_KEY_LENGTH = 12;

    public SetKeyBindings(Main previous) {
        this.previous = previous;
        this.OPTION_BOTTOM_MARGIN = (KeyType.values().length)*2 + OPTION_TOP_MARGIN;
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

        if (!isSet) {
            this.alteredKeyBindings = bindings;
            isSet = true;
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

            resetPressed(bindings, pressed);
            setKey(bindings, pressed);
            exit = true;
        }

        if (exit || moveTimer.isDone()) {
            action = false;
            reset = false;
            moveTimer.reset();
        }
    }

    private void setKey(KeyBindings bindings, boolean[] pressed) {
        int selectedIndex = (selectedX-OPTION_TOP_MARGIN)/2;
        if (selectedIndex == KeyType.values().length) {
            this.alteredKeyBindings.resetKeyboard();
            return;
        }

        KeyType selected = KeyType.values()[selectedIndex];
        resetPressed(bindings, pressed);
        boolean ok = false;
        while (!ok && isSettable(selected)) { // Listen for KeyEvent
            for (int i = 0; i < KeyType.values().length; ++i) {
                KeyType set = KeyType.values()[i];
                if (pressed[set.defaultKeycode] && isSettable(selected) && isSettable(set)) {
                    int defaultSelectedKeycode = defaultKeyBindings.getBinding(selected);
                    int defaultSetKeycode = defaultKeyBindings.getBinding(set);
                    bindings.setBinding(selected, defaultSetKeycode);
                    bindings.setBinding(set, defaultSelectedKeycode);
                    ok = true;
                    break;
                }
                else if ((i+1) == KeyType.values().length/2 || i == KeyType.values().length-1) { // Buffer
                    resetPressed(bindings, pressed);
                }
            }
        }

        resetPressed(bindings, pressed);
    }

    private boolean isSettable(KeyType key) {
        return (key.defaultKeycode != KeyEvent.VK_ENTER && key.defaultKeycode != KeyEvent.VK_ESCAPE
                && key != KeyType.ACTION && key != KeyType.EXIT);
    }

    private void resetPressed (KeyBindings bindings, boolean[] pressed) {
        for (KeyType key : KeyType.values()) {
            pressed[bindings.getBinding(key)] = false;
        }
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
        renderItemBoxes(window, OPTION_TOP_MARGIN, startCol, KeyType.values().length+1);


        /* Render Mappings */
        int startRow = OPTION_TOP_MARGIN;
        startCol += 2;
        for (int i = 0; i < KeyType.values().length; ++i) {
            KeyType key = KeyType.values()[i];
            String keyString = key.toString() + ": ";
            String setting = KeyType.getKey(alteredKeyBindings.getBinding(key));

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

        for (int i = 0; i < DEFAULT.length(); ++i) {
            window[startRow][startCol+i] = new Decal(DEFAULT.charAt(i), Color.BLACK, Color.WHITE);
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
}
