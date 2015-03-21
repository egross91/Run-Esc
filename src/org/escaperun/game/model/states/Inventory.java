package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;

import java.awt.*;

public class Inventory extends GameState {

    private Playing previous;
    private EquipmentContainer equipmentContainer;
    private ItemContainer itemContainer;

    public Inventory(Playing previous, Stage stage) {
        this.previous = previous;
        this.equipmentContainer = stage.getAvatarEquipment();
        this.itemContainer = stage.getAvatarInventory();
    }


    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        boolean inventory = pressed[bindings.getBinding(KeyType.INVENTORY)];
        if (inventory) {
            pressed[bindings.getBinding(KeyType.INVENTORY)] = false;
            return previous;
        }

        handleMovement(bindings, pressed);
        handleAction(bindings, pressed);
        return null;
    }

    private void handleMovement(KeyBindings bindings, boolean[] pressed) {

    }

    private void handleAction(KeyBindings bindings, boolean[] pressed) {

    }

    public static final int INVENTORY_SPACING = 4;
    public static final int EQUIPMENT_SPACING = 2;
    public static final String EQUIPMENT = "EQUIPMENT";
    public static final String INVENTORY = "INVENTORY";
    public static final int TOP_MARGIN = 6;
    public static final int BOTTOM_MARGIN = 13;
    public static final int LEFT_MARGIN = 5;
    public static final int RIGHT_MARGIN = 5;
    private static final int NUM_GOOD_COLUMNS = GameWindow.COLUMNS-LEFT_MARGIN-RIGHT_MARGIN;


    @Override
    public Decal[][] getRenderable() {
        Decal[][] window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];

        /* Draw Equipment */
        renderContainer(window, equipmentContainer, EQUIPMENT, TOP_MARGIN, EQUIPMENT_SPACING, 1, equipmentContainer.getCapacity());

        /* Draw Inventory */
        int startRow = 12; // The total space taken by Equipment.
        int numRows = itemContainer.getCapacity() / 5;
        int numCols = itemContainer.getCapacity() / 6;
        renderContainer(window, itemContainer, INVENTORY, startRow, INVENTORY_SPACING, numRows, numCols);

        return window;
    }

    private void renderContainer(Decal[][] window, ItemContainer container, String label, int startRow,
                                 int spacing, int numRows, int numCols) {
        int startColumn = NUM_GOOD_COLUMNS/2 - label.length()/2;
        for (int i = 0; i < label.length(); i++) {
            window[startRow][startColumn+i+LEFT_MARGIN] = new Decal(label.charAt(i), Color.BLACK, Color.WHITE);
        }
        startRow += spacing;

        int itemIndex = 0;
        int size = numCols;
        int charsUsed = size + spacing*(size-1);
        for (int i = 0; i < numRows; ++i) {
            startColumn = (NUM_GOOD_COLUMNS /2) - (charsUsed/2);
            for (int j = 0; j < numCols; ++j) {
                TakeableItem item = container.get(itemIndex++);
                Decal render;
                if (item == null) {
                    render = Decal.EMPTY_ITEM_SLOT;
                }
                else {
                    render = item.getRenderable()[0][0];
                }

                window[startRow][startColumn+LEFT_MARGIN] = render;
                startColumn += spacing+1;
            }

            startRow += spacing;
        }
    }
}
