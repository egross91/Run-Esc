package org.escaperun.game.view;

import org.escaperun.game.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame {

    public static final int ROWS = 51;
    public static final int COLUMNS = 85;

    private Renderable renderable;
    private ConsolePanel cp;

    public GameWindow(Renderable renderable, KeyListener keylistener) {
        super("Run-Escape");
        this.renderable = renderable;
        this.cp = new ConsolePanel(ROWS, COLUMNS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keylistener);
        getContentPane().add(cp);
        pack();
        setVisible(true);
    }

    public void render() throws IllegalArgumentException {
        cp.clear();

        Decal[][] toRender = renderable.getRenderable();
        if (toRender != null) {
            for (int i = 0; i < Math.min(toRender.length, ROWS); i++) {
                for (int j = 0; j < Math.min(toRender[i].length, COLUMNS); j++) {
                    if (toRender[i][j] != null) {
                        Color back = toRender[i][j].background;
                        Color front = toRender[i][j].foreground;
                        char ch = toRender[i][j].ch;
                        cp.setBackgroundColor(back, i, j);
                        cp.setForegroundColor(front, i, j);
                        cp.setChar(ch, i, j);
                    }
                }
            }
        }

        cp.repaint();
    }
}
