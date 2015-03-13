package org.escaperun.game.model.entities.npc;

import org.escaperun.game.model.entities.Avatar;
import org.escaperun.game.model.stage.tile.Tile;

import java.util.ArrayList;

public abstract class AI {
    public abstract void takeStagePrivateInfo(Tile[][] grid, ArrayList<NPC> npCs, Avatar avatar);
    public abstract void update();
}
