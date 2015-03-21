package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.EquipableItem;
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


    @Override
    public Decal[][] getRenderable() {
        Decal[][] window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];
        int numGoodColumns = GameWindow.COLUMNS-LEFT_MARGIN-RIGHT_MARGIN;

        /* Draw Equipment */
        int startRow = TOP_MARGIN;
        int startColumn = numGoodColumns/2 - EQUIPMENT.length()/2;
        for (int i = 0; i < EQUIPMENT.length(); i++) {
            window[startRow][startColumn+i+LEFT_MARGIN] = new Decal(EQUIPMENT.charAt(i), Color.BLACK, Color.WHITE);
        }
        startRow += EQUIPMENT_SPACING;
        int equipSize = equipmentContainer.getCapacity();
        int charsUsed = equipSize + EQUIPMENT_SPACING*(equipSize-1);
        startColumn = numGoodColumns/2-charsUsed/2;
        for (int i = 0; i < equipmentContainer.getCapacity(); i++) {
            TakeableItem item = equipmentContainer.get(i);
            Decal render = null;
            if (item == null) {
                render = new Decal('_', Color.BLACK, Color.WHITE);
            } else {
                render = item.getRenderable()[0][0];
            }
            window[startRow][startColumn+LEFT_MARGIN] = render;
            startColumn += EQUIPMENT_SPACING+1;
        }
        startRow += INVENTORY_SPACING;

        startColumn = numGoodColumns/2 - INVENTORY.length()/2;
        for (int i = 0; i < INVENTORY.length(); i++) {
            window[startRow][startColumn+i+LEFT_MARGIN] = new Decal(INVENTORY.charAt(i), Color.BLACK, Color.WHITE);
        }
        startRow += EQUIPMENT_SPACING;

        //TODO: MORE
        return window;
    }
}
