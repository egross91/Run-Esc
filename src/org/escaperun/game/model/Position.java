package org.escaperun.game.model;

import javafx.geometry.Pos;

public final class Position {

    public final int x;
    public final int y;

    public Position(int x, int y) {
        if (x < 0)
            throw new IllegalArgumentException("Position cannot have a negative x.");
        if (y < 0)
            throw new IllegalArgumentException("Position cannot have a negative y.");
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position pos) {
        if (x == pos.x)
            if (y == pos.y)
                return true;
        return false;
    }
}
