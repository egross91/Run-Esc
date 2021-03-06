package org.escaperun.game.controller;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyboardListener;
import org.escaperun.game.model.Game;
import org.escaperun.game.serialization.SaveManager;
import org.escaperun.game.view.GameWindow;

public class RunGame implements Runnable {

    public static void main(String[] args) {
        RunGame rg = new RunGame();
//        Sound.INTRO_MUSIC.play();
        rg.run();
    }
    public static final String KEY_BINDINGS_FILE = System.getProperty("user.dir") + "/profiles/keys.cfg";
    public static final double SECONDS_PER_TICK = 1/60.0; // goal is 60 fps...each 'frame' should take 1/60th of a second
    private KeyboardListener keyboard;
    private Game game;
    private GameWindow window;

    public RunGame() {
        keyboard = new KeyboardListener();
        KeyBindings load = null;
        try {
            load = SaveManager.load(KEY_BINDINGS_FILE, new KeyBindings());
        } catch (Exception ex) {
        }
        if (load == null) load = new KeyBindings();
        game = new Game(load);
        window = new GameWindow(game, keyboard);
    }

    private void cleanup() {
        KeyBindings bind = game.getKeyBindings();
        try {
            SaveManager.save(KEY_BINDINGS_FILE, bind);
        } catch (Exception ex) {

        }
    }

    public void run() {
        boolean running = true;
        long last = System.currentTimeMillis(); // time from last tick
        double unprocessed = 0.0; // unprocessed time

        while (running) {
            long current = System.currentTimeMillis();
            long elapsed = current-last; // time between last

            last = current;
            unprocessed += elapsed/1000.0;

            boolean update = false;
            while (unprocessed >= SECONDS_PER_TICK) {
                game.update(keyboard.pressed);
                if (game.isOver()) {
                    cleanup();
                    window.dispose();
                    return;
                }
                unprocessed -= SECONDS_PER_TICK;
                update = true;
            }
            if (update) {
                window.render();
            } else {
                try {
                    // If we didn't do any work let the CPU catch
                    // up on some cycles... or something
                    Thread.sleep(1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    running = false;
                }
            }
        }
    }
}