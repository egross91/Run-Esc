package org.escaperun.game.model.items.options;

public class ItemOption {
    public final String display;
    public final Runnable action;

    public final static ItemOption NEVERMIND = new ItemOption("Never Mind", new Runnable() {
        @Override
        public void run() {

        }
    });

    public ItemOption(String display, Runnable action) {
        this.display = display;
        this.action = action;
    }
}
