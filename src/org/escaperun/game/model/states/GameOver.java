package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.options.LoggerOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;

import java.awt.*;

public class GameOver extends GameState{

    private Timer moveTimer = new Timer(8);
    private Playing previous;
    private Stage stage;

    public GameOver(Playing previous, Stage stage){
        this.previous = previous;
        this.stage = stage;
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        LoggerOption.getInstance().update(null, null);
        boolean exit = pressed[bindings.getBinding(KeyType.EXIT)];
        if(exit){
            pressed[bindings.getBinding(KeyType.EXIT)] = false;
            return new Pause(previous);

        }
            return null;
    }


    @Override
    public Decal[][] getRenderable() {

        Decal[][] window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];
        int row = GameWindow.ROWS;
        int column = GameWindow.COLUMNS;
        window[(row) / 4][(column / 2) - 7] = new Decal(' ', Color.BLACK, Color.WHITE);
         window[(row) / 4][(column / 2) - 6] = new Decal((char) 197, Color.BLACK, Color.RED);
        window[(row) / 4][(column / 2) - 5] = new Decal(' ', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) - 4] = new Decal('G', Color.BLACK, Color.WHITE);
       window[(row) / 4][(column / 2) - 3] = new Decal('A', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) - 2] = new Decal('M', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) - 1] = new Decal('E', Color.BLACK, Color.WHITE);
        window[(row) / 4][column / 2] = new Decal(' ', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) + 1] = new Decal('O', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) + 2] = new Decal('V', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) + 3] = new Decal('E', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) + 4] = new Decal('R', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) + 5] = new Decal(' ', Color.BLACK, Color.WHITE);
        window[(row) / 4][(column / 2) + 6] = new Decal((char) 197, Color.BLACK, Color.RED);
        window[(row) / 4][(column / 2) + 7] = new Decal(' ', Color.BLACK, Color.WHITE);
        return window;
    }
}
