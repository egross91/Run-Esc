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
        //Calculate angle from delta
        if (dx == 0 && dy == 0)
            return null;
        if (dx == 0) {
            if (dy < 0)
                return WEST;
            else
                return EAST;
        }
        if (dy == 0) {
            if (dx < 0)
                return NORTH;
            else
                return SOUTH;
        }
        //calculate angle
        double rad = Math.atan2(-dx, dy);
        double deg = (rad * (180/Math.PI));
        if (deg < 0)
            deg += 360;
        if (deg >= 22.5 && deg < 67.5)
            return NORTHEAST;
        if (deg >= 67.5 && deg < 112.5)
            return NORTH;
        if (deg >= 112.5 && deg < 157.5)
            return NORTHWEST;
        if (deg >= 157.5 && deg < 202.5)
            return WEST;
        if (deg >= 202.5 && deg < 247.5)
            return SOUTHWEST;
        if (deg >= 247.5 && deg < 337.5)
            return SOUTH;
        if (deg >= 337.5 || deg < 22.5)
            return SOUTHEAST;
        return null;
    }
}
