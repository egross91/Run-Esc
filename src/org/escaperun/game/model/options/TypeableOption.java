package org.escaperun.game.model.options;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.states.GameState;

public class TypeableOption extends Option {

    private Timer typeTimer = new Timer(100); // TODO: Fix timer

    public TypeableOption(String text, int pad) {
        super(text+getPadding(pad));
    }

    @Override
    public GameState update(KeyBindings bind, boolean[] pressed) {
        typeTimer.tick();

        //TODO: Typing logic (just take old stuff)

        return null;
    }

    private static String getPadding(int pad) {
        char[] val = new char[pad];
        for (int i = 0; i < pad; i++) val[i] = ' ';
        return String.valueOf(val);
    }
}