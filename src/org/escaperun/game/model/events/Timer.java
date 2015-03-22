package org.escaperun.game.model.events;

public final class Timer {

    private int ticksSince;
    private int ticksTo;

    public Timer(int ticksTo) {
        this.ticksTo = ticksTo;
    }

    public final void tick() {
        ticksSince++;
    }

    public final void reset() {
        ticksSince = 0;
    }

    public boolean isDone() {
        return ticksSince >= ticksTo;
    }

    public int getTicksSince() {
        return ticksSince;
    }

    public int getTicksTo() {
        return ticksTo;
    }
}
