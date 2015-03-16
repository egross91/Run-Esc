package org.escaperun.game.model.entities.npc.ai;

import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.npc.NPC;
import org.escaperun.game.model.stage.Stage;

import java.util.Random;

public abstract class AI {
    private final int[][] possibleDeltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private static final Random random = new Random();

    public Position getNewPosition(Stage stage, NPC e) {
        Position current = e.getCurrentPosition();

        for (int attempt = 0; attempt < 4; ++attempt) {
            int[] d = possibleDeltas[random.nextInt(8)];

            Position candidate = new Position(current.x + d[0], current.y + d[1]);

            if (stage.isValidMove(candidate)) {
               return candidate;
            }
        }

        // Don't move.
        return current;
    }

    public int[][] getPossibleDeltas() {
        return this.possibleDeltas;
    }

    public static Random getRandomGenerator() {
        return random;
    }
}
