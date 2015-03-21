package org.escaperun.game.model;

import javafx.geometry.Pos;

public final class Position {

    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Position pos) {
        if (x == pos.x)
            if (y == pos.y)
                return true;
        return false;
    }

    public static int calcuateDistance(Position a, Position b) {
        double xsquare = Math.pow((a.x - b.x),2);
        double ysquare = Math.pow((a.y-b.y),2);
        return (int)Math.sqrt((xsquare + ysquare));
    }
}
