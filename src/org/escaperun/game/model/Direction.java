package org.escaperun.game.model;

public enum Direction {

    EAST(0, 1),
    NORTHEAST(-1, 1),
    NORTH(-1, 0),
    NORTHWEST(-1, -1),
    WEST(0, -1),
    SOUTHWEST(1, -1),
    SOUTH(1, 0),
    SOUTHEAST(1, 1);

    private Position delta;

    Direction(int deltaX, int deltaY) {
        this.delta = new Position(deltaX, deltaY);
    }

    public Position getDelta() {
        return delta;
    }

    public static Direction fromDelta(int dx, int dy) {
        for (Direction d : values()) {
            if (d.getDelta().x == dx && d.getDelta().y == dy) return d;
        }
        return null;
    }
}
