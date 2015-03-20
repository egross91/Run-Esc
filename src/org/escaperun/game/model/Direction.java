package org.escaperun.game.model;

public enum Direction {

    EAST(0, 0, 1),
    NORTHEAST(45, -1, 1),
    NORTH(90, -1, 0),
    NORTHWEST(135, -1, -1),
    WEST(180, 0, -1),
    SOUTHWEST(225, 1, -1),
    SOUTH(270, 1, 0),
    SOUTHEAST(315, 1, 1);

    private int angle;
    private Position delta;

    Direction(int angle, int deltaX, int deltaY) {
        this.angle = angle;
        this.delta = new Position(deltaX, deltaY);
    }

    public int getAngle(){
        return angle;
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
