package org.escaperun.game.model.states;

import org.escaperun.game.controller.Sound;
import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.Tickable;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;
import org.escaperun.game.view.Renderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Main extends GameState {

    private OptionContainer optionContainer;
    private FlameGenerator flame = new FlameGenerator();

    public Main() {
        Option[][] options = new Option[][]{
                {new SelectableOption("New Game") {

                    @Override
                    public GameState getNextState() {
                        return new Creation();
                    }
                }},
                {new SelectableOption("Load Game") {

                    @Override
                    public GameState getNextState() {
                        return null;
                    }
                }},
                {new SelectableOption("Exit") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                }}
        };
        optionContainer = new OptionContainer(options, OptionContainer.ContainerType.CENTERED);
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        flame.tick();
        return optionContainer.update(bindings, pressed);
    }

    @Override
    public Decal[][] getRenderable() {
        Decal[][] window = optionContainer.getRenderable();

        for (Flame f : flame.flame) {
            window[f.pos.x][f.pos.y] = f.getRenderable()[0][0];
        }

        int row = GameWindow.ROWS/2+1;

        int col = GameWindow.COLUMNS/5;
        for (int dx = -3; dx <= 3; dx++) {
            window[row][col+dx] = new Decal('\u0000', Color.GRAY, Color.GRAY);
        }
        for (int dx = 0; dx <= 7; dx++) {
            window[row+dx][col] = new Decal('\u0000', Color.GRAY, Color.GRAY);
        }

        window[row-1][col-4] = new Decal('\u0000', Color.GRAY, Color.GRAY);
        window[row-1][col+4] = new Decal('\u0000', Color.GRAY, Color.GRAY);

        col = 4*col;
        for (int dx = -3; dx <= 3; dx++) {
            window[row][col+dx] = new Decal('\u0000', Color.GRAY, Color.GRAY);
        }
        for (int dx = 0; dx <= 7; dx++) {
            window[row+dx][col] = new Decal('\u0000', Color.GRAY, Color.GRAY);
        }

        window[row-1][col-4] = new Decal('\u0000', Color.GRAY, Color.GRAY);
        window[row-1][col+4] = new Decal('\u0000', Color.GRAY, Color.GRAY);

        //draw chalices
        return window;
    }
}
class FlameGenerator implements Tickable{

    ArrayList<Flame> flame = new ArrayList<Flame>();
    private static int FLAMES_WE_WANT = 30;
    private static Random RAND = new Random();
    private Timer t = new Timer(3);

    @Override
    public void tick() {
        for (Flame f : flame) f.tick();
        ArrayList<Flame> keep = new ArrayList<Flame>();
        for (Flame f : flame) {
            if (!f.isDead()) {
                keep.add(f);
            }
        }
        flame = keep;
        t.tick();
        if (t.isDone()) {
            t.reset();

            //left flame:

            int col = GameWindow.COLUMNS/5;

            int rnd = RAND.nextInt(5)-2;

            int posX = GameWindow.ROWS/2;
            int posY = col+rnd;

            keep.add(new Flame(new Position(posX, posY)));

            //right flame:

            rnd = RAND.nextInt(5)-2;
            col *=4;

            keep.add(new Flame(new Position(posX, col+rnd)));
        }


    }
}
class Flame implements Tickable, Renderable {
    private static Random rnd = new Random();
    private static Decal[] DECALS = new Decal[] {
            new Decal('\u0000', Color.RED, Color.RED),
            new Decal('\u0000', Color.RED.brighter(), Color.RED),
            new Decal('\u0000', Color.ORANGE.brighter(), Color.RED),
            new Decal('\u0000', Color.ORANGE.brighter().brighter(), Color.RED),
            new Decal('\u0000', Color.BLUE.brighter(), Color.RED),
            new Decal('\u0000', Color.BLUE.brighter(), Color.RED),
    };
    private int decalPos = 0;
    Position pos;
    private Timer moveAmount = new Timer(rnd.nextInt(8)+1);
    private Timer moveTimer = new Timer(rnd.nextInt(4)+3);

    public Flame(Position pos) {
        this.pos = pos;
    }

    public boolean isDead() {
        return moveAmount.isDone();
    }

    public void tickColor() {
        if (rnd.nextInt(2) == 0) {
            //change color!
            int add = rnd.nextInt(2);
            if (rnd.nextInt(2)==0)add++;
            if ((decalPos+add)<DECALS.length) {
                decalPos += add;
            }
        }
    }

    public void tickMove() {
        if (moveAmount.isDone()) return;
        if (moveTimer.isDone()) {
            moveTimer.reset();
            tickColor();

            Position next = new Position(pos.x-1, pos.y+rnd.nextInt(3)-1);
            pos = next;
            //MOVE:
            moveAmount.tick();
        }
        moveTimer.tick();
    }

    @Override
    public Decal[][] getRenderable() {
        return new Decal[][] { {DECALS[decalPos]} };
    }

    @Override
    public void tick() {
        tickMove();
    }
}