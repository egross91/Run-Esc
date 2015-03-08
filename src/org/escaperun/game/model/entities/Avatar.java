package org.escaperun.game.model.entities;

import org.escaperun.game.model.Position;

public class Avatar extends Entity{

    protected Avatar(Position initialposition) {
        super(initialposition);
    }

    @Override
    public void move(Position p) {
        setCurrentPosition(p);
    }
}
