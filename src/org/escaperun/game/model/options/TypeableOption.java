package org.escaperun.game.model.options;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.view.Decal;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TypeableOption extends Option {

    public static final int HORIZONTAL_SPACING = 1;

    private String baseText;
    private String typedText;
    private Timer typeTimer = new Timer(10);
    private Timer backspaceTimer = new Timer(8);
    private Timer flashTimer = new Timer(25);
    private int max;
    private boolean flash = false;
    private char lastPressed;

    public TypeableOption(String text, int maxChars) {
        baseText = text;
        typedText = "";
        permanentFocus = true;
        max = maxChars;
    }

    @Override
    public Decal[][] getRenderable(boolean focused) {
        Decal[][] ret = new Decal[1][baseText.length()+HORIZONTAL_SPACING+(max+1)+1];

        for (int i = 0; i < baseText.length(); i++) {
            ret[0][i] = new Decal(baseText.charAt(i), Color.BLACK, Color.WHITE);
        }
        int startX = baseText.length()+HORIZONTAL_SPACING;
        for (int i = 0; i < typedText.length(); i++) {
            ret[0][startX+i] = new Decal(typedText.charAt(i), Color.BLACK, Color.WHITE);
        }
        startX += typedText.length();
        if (flash) {
            ret[0][startX] = new Decal('\u0000', Color.WHITE, Color.WHITE);
        } else {
            ret[0][startX] = new Decal('\u0000', Color.BLACK, Color.BLACK);
        }
        return ret;
    }

    @Override
    public GameState update(KeyBindings bind, boolean[] pressed) {
        typeTimer.tick();
        flashTimer.tick();
        backspaceTimer.tick();

        if (isCharPressed(pressed) && typedText.length() <= max) {
            char ch = getCharPressed(pressed);
            if (ch != lastPressed || typeTimer.isDone()) {
                typedText += ch;
                lastPressed = ch;
                typeTimer.reset();
            }
        }

        boolean backspace = pressed[bind.getBinding(KeyType.BACKSPACE)];
        if (backspace && backspaceTimer.isDone()) {
            typedText = typedText.substring(0, typedText.length()-1);
            backspaceTimer.reset();
        }

        boolean enter = pressed[bind.getBinding(KeyType.ACTION)];
        if (enter && typedText.length() > 0) {
            //TODO: Make it do something that's overridable
        }

        if (flashTimer.isDone()) {
            flash = !flash;
            flashTimer.reset();
        }
        return null;
    }

    private boolean isCharPressed(boolean[] pressed) {
        for (char ch = 'A'; ch <= 'z'; ch++) {
            if (isPressed(ch, pressed)) {
                return true;
            }
        }
        return false;
    }

    private char getCharPressed(boolean[] pressed) {
        for (char ch = 'A'; ch <= 'z'; ch++) {
            if (isPressed(ch, pressed)) {
                return ch;
            }
        }
        return '\u0000';
    }

    private boolean isPressed(char ch, boolean[] pressed) {
        switch (ch) {
            case 'a':
                return pressed[KeyEvent.VK_A];
            case 'A':
                return pressed[KeyEvent.VK_A] && pressed[KeyEvent.VK_SHIFT];
            case 'b':
                return pressed[KeyEvent.VK_B];
            case 'B':
                return pressed[KeyEvent.VK_B] && pressed[KeyEvent.VK_SHIFT];
            case 'c':
                return pressed[KeyEvent.VK_C];
            case 'C':
                return pressed[KeyEvent.VK_C] && pressed[KeyEvent.VK_SHIFT];
            case 'd':
                return pressed[KeyEvent.VK_D];
            case 'D':
                return pressed[KeyEvent.VK_D] && pressed[KeyEvent.VK_SHIFT];
            case 'e':
                return pressed[KeyEvent.VK_E];
            case 'E':
                return pressed[KeyEvent.VK_E] && pressed[KeyEvent.VK_SHIFT];
            case 'f':
                return pressed[KeyEvent.VK_F];
            case 'F':
                return pressed[KeyEvent.VK_F] && pressed[KeyEvent.VK_SHIFT];
            case 'g':
                return pressed[KeyEvent.VK_G];
            case 'G':
                return pressed[KeyEvent.VK_G] && pressed[KeyEvent.VK_SHIFT];
            case 'h':
                return pressed[KeyEvent.VK_H];
            case 'H':
                return pressed[KeyEvent.VK_H] && pressed[KeyEvent.VK_SHIFT];
            case 'i':
                return pressed[KeyEvent.VK_I];
            case 'I':
                return pressed[KeyEvent.VK_I] && pressed[KeyEvent.VK_SHIFT];
            case 'j':
                return pressed[KeyEvent.VK_J];
            case 'J':
                return pressed[KeyEvent.VK_J] && pressed[KeyEvent.VK_SHIFT];
            case 'k':
                return pressed[KeyEvent.VK_K];
            case 'K':
                return pressed[KeyEvent.VK_K] && pressed[KeyEvent.VK_SHIFT];
            case 'l':
                return pressed[KeyEvent.VK_L];
            case 'L':
                return pressed[KeyEvent.VK_L] && pressed[KeyEvent.VK_SHIFT];
            case 'm':
                return pressed[KeyEvent.VK_M];
            case 'M':
                return pressed[KeyEvent.VK_M] && pressed[KeyEvent.VK_SHIFT];
            case 'n':
                return pressed[KeyEvent.VK_N];
            case 'N':
                return pressed[KeyEvent.VK_N] && pressed[KeyEvent.VK_SHIFT];
            case 'o':
                return pressed[KeyEvent.VK_O];
            case 'O':
                return pressed[KeyEvent.VK_O] && pressed[KeyEvent.VK_SHIFT];
            case 'p':
                return pressed[KeyEvent.VK_P];
            case 'P':
                return pressed[KeyEvent.VK_P] && pressed[KeyEvent.VK_SHIFT];
            case 'q':
                return pressed[KeyEvent.VK_Q];
            case 'Q':
                return pressed[KeyEvent.VK_Q] && pressed[KeyEvent.VK_SHIFT];
            case 'r':
                return pressed[KeyEvent.VK_R];
            case 'R':
                return pressed[KeyEvent.VK_R] && pressed[KeyEvent.VK_SHIFT];
            case 's':
                return pressed[KeyEvent.VK_S];
            case 'S':
                return pressed[KeyEvent.VK_S] && pressed[KeyEvent.VK_SHIFT];
            case 't':
                return pressed[KeyEvent.VK_T];
            case 'T':
                return pressed[KeyEvent.VK_T] && pressed[KeyEvent.VK_SHIFT];
            case 'u':
                return pressed[KeyEvent.VK_U];
            case 'U':
                return pressed[KeyEvent.VK_U] && pressed[KeyEvent.VK_SHIFT];
            case 'v':
                return pressed[KeyEvent.VK_V];
            case 'V':
                return pressed[KeyEvent.VK_V] && pressed[KeyEvent.VK_SHIFT];
            case 'w':
                return pressed[KeyEvent.VK_W];
            case 'W':
                return pressed[KeyEvent.VK_W] && pressed[KeyEvent.VK_SHIFT];
            case 'x':
                return pressed[KeyEvent.VK_X];
            case 'X':
                return pressed[KeyEvent.VK_X] && pressed[KeyEvent.VK_SHIFT];
            case 'y':
                return pressed[KeyEvent.VK_Y];
            case 'Y':
                return pressed[KeyEvent.VK_Y] && pressed[KeyEvent.VK_SHIFT];
            case 'z':
                return pressed[KeyEvent.VK_Z];
            case 'Z':
                return pressed[KeyEvent.VK_Z] && pressed[KeyEvent.VK_SHIFT];
            default:
                return false;
        }
    }
}