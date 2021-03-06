package org.escaperun.game.model.options;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;

import java.awt.*;
import java.util.*;

public class LoggerOption extends Option {

    public static LoggerOption _loggerOption = new LoggerOption();
    private static int MESSAGES_TO_SHOW = 5;
    private static int LENGTH_TO_SHOW = 250;
    private Queue<Pair> current = new LinkedList<Pair>();
    private Queue<Pair> currentR = new LinkedList<Pair>();
    private LoggerOption() {

    }

    public static LoggerOption getInstance() {
        return _loggerOption;
    }

    @Override
    public Decal[][] getRenderable(boolean focused) {
        Decal[][] ret = new Decal[MESSAGES_TO_SHOW][GameWindow.COLUMNS];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[i].length; j++) {
                ret[i][j] = Decal.BLANK;
            }
        }

        int idx = 0;
        for (Pair p : current) {
            String toDraw = p.message;
            for (int col = 0; col < toDraw.length(); col++) {
                ret[idx][col] = new Decal(toDraw.charAt(col), Color.BLACK, Color.RED);
            }
            idx++;
        }

        idx = 0;
        for (Pair p : currentR) {
            String toDraw = p.message;
            for (int col = 0; col < toDraw.length(); col++) {
                ret[idx][(GameWindow.COLUMNS/2)+5+col] = new Decal(toDraw.charAt(col), Color.BLACK, Color.GREEN);
            }
            idx++;
        }
        return ret;
    }

    @Override
    public GameState update(KeyBindings bind, boolean[] pressed) {
        for (Pair p : current) p.timer.tick();
        for (Pair p : currentR) p.timer.tick();

        while (!current.isEmpty()) {
            Pair peak = current.peek();
            if (peak.timer.isDone()) current.poll();
            else break;
        }
        while(!currentR.isEmpty()){
            Pair peak = currentR.peek();
            if(peak.timer.isDone()) currentR.poll();
            else break;
        }

        while (current.size() < MESSAGES_TO_SHOW && !Logger.getInstance().isEmpty()) {
            current.add(new Pair(Logger.getInstance().pollFront(), new Timer(LENGTH_TO_SHOW)));
        }
        while(currentR.size() < MESSAGES_TO_SHOW && !Logger.getInstance().isRightEmpty()){
            currentR.add(new Pair(Logger.getInstance().pollFrontRight(), new Timer(LENGTH_TO_SHOW)));
        }
        return null;
    }

    private class Pair {

        private String message;
        private Timer timer;

        public Pair(String message, Timer timer) {
            this.message = message;
            this.timer = timer;
        }
    }
}
