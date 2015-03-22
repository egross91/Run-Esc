package org.escaperun.game.model.states;

import org.escaperun.game.controller.Logger;
import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.statistics.*;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.options.LoggerOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;

import java.awt.*;

public class Inventory extends GameState {

    private Playing previous;
    private EquipmentContainer equipmentContainer;
    private ItemContainer itemContainer;
    private Timer moveTimer = new Timer(6);
    private Pair displayInfo;

    private int selectedX = 0, selectedY= 0;
    private final int EQUIPMENT_ORIGIN_ROW = 8;
    private final int EQUIPMENT_ORIGIN_COL = 34;
    private final int EQUIPMENT_MAX_COL = 49;
    private final int INVENTORY_ORIGIN_ROW = 14;
    private final int INVENTORY_ORIGIN_COL = 36;
    private final int INVENTORY_MAX_ROW = 24;
    private final int INVENTORY_MAX_COL = 48;

    public Inventory(Playing previous, Stage stage) {
        this.previous = previous;
        this.equipmentContainer = stage.getAvatarEquipment();
        this.itemContainer = stage.getAvatarInventory();
        setSelectedEquipmentOrigin();
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        LoggerOption.getInstance().update(null, null);
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
        boolean up = pressed[bindings.getBinding(KeyType.UP)];
        boolean down = pressed[bindings.getBinding(KeyType.DOWN)];
        boolean right = pressed[bindings.getBinding(KeyType.RIGHT)];
        boolean left = pressed[bindings.getBinding(KeyType.LEFT)];

        int moveX = selectedX;
        int moveY = selectedY;

        if (up) moveX -= 2;
        if (down) moveX += 2;
        if (left) moveY -= 3;
        if (right) moveY += 3;

        moveTimer.tick();

        if (moveTimer.isDone()) {
            if (moveX > EQUIPMENT_ORIGIN_ROW && moveX < INVENTORY_ORIGIN_ROW && (selectedX - moveX) > 0) {
                setSelectedEquipmentOrigin();
            } else if (moveX < INVENTORY_ORIGIN_ROW && moveX > EQUIPMENT_ORIGIN_ROW && (selectedX - moveX) < 0) {
                setSelectedInventoryOrigin();
            }
            else {
                 if (moveX <= EQUIPMENT_ORIGIN_ROW) {
                    moveX = EQUIPMENT_ORIGIN_ROW;

                    if (moveY <= EQUIPMENT_ORIGIN_COL) {
                        moveY = EQUIPMENT_ORIGIN_COL;
                    } else if (moveY >= EQUIPMENT_MAX_COL) {
                        moveY = EQUIPMENT_MAX_COL;
                    }
                } else {
                    if (moveX > INVENTORY_MAX_ROW) {
                        moveX = INVENTORY_MAX_ROW;
                    }
                    if (moveY > INVENTORY_MAX_COL) {
                        moveY = INVENTORY_MAX_COL;
                    } else if (moveY < INVENTORY_ORIGIN_COL) {
                        moveY = INVENTORY_ORIGIN_COL;
                    }
                }

                selectedX = moveX;
                selectedY = moveY;
            }

            moveTimer.reset();
        }
    }
    
    private void handleAction(KeyBindings bindings, boolean[] pressed) {
        boolean enter = pressed[bindings.getBinding(KeyType.ACTION)];

        if (enter) {
            renderItemOptions(displayInfo.container, displayInfo.container.get(displayInfo.index));


            pressed[bindings.getBinding(KeyType.ACTION)] = false;
        }
    }

    public static final int SPACING = 2;
    public static final String EQUIPMENT = "EQUIPMENT";
    public static final String INVENTORY = "INVENTORY";
    public static final String ITEM = "ITEM: ";
    public static final String DESCRIPTION = "DESCRIPTION: ";
    public static final String NO_ITEM = "NO ITEM";
    public static final int TOP_MARGIN = 6;
    public static final int BOTTOM_MARGIN = 13;
    public static final int LEFT_MARGIN = 5;
    public static final int RIGHT_MARGIN = 5;

    private static final int NUM_GOOD_COLUMNS = GameWindow.COLUMNS-LEFT_MARGIN-RIGHT_MARGIN;


    @Override
    public Decal[][] getRenderable() {
        Decal[][] window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];

        /* Draw Equipment */
        renderContainer(window, equipmentContainer, EQUIPMENT, TOP_MARGIN, 1, equipmentContainer.getCapacity());

        /* Draw Inventory */
        int startRow = 12; // The total space taken by Equipment.
        int numRows = itemContainer.getCapacity() / 5;
        int numCols = itemContainer.getCapacity() / 6;
        renderContainer(window, itemContainer, INVENTORY, startRow, numRows, numCols);

        /* Draw Item Info */
        renderDisplayItem(window, displayInfo, INVENTORY_MAX_ROW+4, LEFT_MARGIN);

        /* Log */
        Decal[][] log = LoggerOption.getInstance().getRenderable(false);

        for (int i = 0; i < log.length; i++) {
            for (int j = 0; j < log[i].length; j++) {
                window[i][j] = log[i][j];
            }
        }

        return window;
    }

    private void renderContainer(Decal[][] window, ItemContainer container, String label, int startRow, int numRows, int numCols) {
        int startColumn = NUM_GOOD_COLUMNS/2 - label.length()/2;
        for (int i = 0; i < label.length(); i++) {
            window[startRow][startColumn+i+LEFT_MARGIN] = new Decal(label.charAt(i), Color.BLACK, Color.WHITE);
        }
        startRow += SPACING;

        int itemIndex = 0;
        int size = numCols;
        int charsUsed = size + SPACING*(size-1);
        for (int i = 0; i < numRows; ++i) {
            startColumn = (NUM_GOOD_COLUMNS/2) - (charsUsed/2);
            for (int j = 0; j < numCols; ++j) {
                TakeableItem item = container.get(itemIndex++);
                Decal render;
                if (item == null) {
                    render = Decal.EMPTY_ITEM_SLOT;
                }
                else {
                    render = item.getRenderable()[0][0];
                }

                // Render red if it is the selected item.
                if (startRow == selectedX && startColumn+LEFT_MARGIN == selectedY) {
                    render = new Decal(render.ch, render.background, Color.RED);
                    displayInfo = null;
                    displayInfo = new Pair(itemIndex-1, container);
                }

                window[startRow][startColumn+LEFT_MARGIN] = render;
                startColumn += SPACING+1;
            }

            startRow += SPACING;
        }
    }

    private void renderDisplayItem(Decal[][] window, Pair displayInfo, int startRow, int startCol) {
        TakeableItem item = displayInfo.container.get(displayInfo.index);

        if (item == null) {
            String displayName = ITEM + NO_ITEM;
            for (int i = 0; i < displayName.length(); ++i) {
                window[startRow][startCol+i] = new Decal(displayName.charAt(i), Color.BLACK, Color.WHITE);
            }
        }
        else {
            String displayName = ITEM + item.getName();
            for (int i = 0; i < displayName.length(); ++i) {
                window[startRow][startCol+i] = new Decal(displayName.charAt(i), Color.BLACK, Color.WHITE);
            }
            ++startRow;

            String displayDesc = DESCRIPTION + item.getDescription();
            for (int i = 0; i < displayDesc.length(); ++i) {
                window[startRow][startCol+i] = new Decal(displayDesc.charAt(i), Color.BLACK, Color.WHITE);
            }
            startRow += 2;

            StatisticContainer stats = item.getStatistics();
            Decal[][] decals = filterStats(stats);
            int colsUsed = 0;
            for (int i = 0; i < decals.length; ++i) {
                for (int j = 0; j < decals[i].length; ++j) {
                    window[startRow][startCol+colsUsed++] = decals[i][j];
                }

                if (((i+1)%2 == 0) && i != 0) {
                    ++startRow;
                    colsUsed = 0;
                }
                else {
                    for (int j = decals[i].length; j < 30; ++j) {
                        window[startRow][startCol+j] = Decal.BLANK;
                        ++colsUsed;
                    }
                }
            }
        }
    }

    private void renderItemOptions(EquipmentContainer equipment, EquipableItem toUnEquip) {
        // TODO
    }

    private void renderItemOptions(ItemContainer inventory, EquipableItem toEquip) {
        // TODO
    }

    private void renderItemOptions(ItemContainer inventory, TakeableItem useableItem) {
        // TODO
    }

    private void setSelectedEquipmentOrigin() {
        this.selectedX = EQUIPMENT_ORIGIN_ROW;
        this.selectedY = EQUIPMENT_ORIGIN_COL;
    }

    private void setSelectedInventoryOrigin() {
        this.selectedX = INVENTORY_ORIGIN_ROW;
        this.selectedY = INVENTORY_ORIGIN_COL;
    }

    private Decal[][] filterStats(StatisticContainer stats) {
        Decal[][] decals = new Decal[8][];
        decals[0] = stats.renderize(stats.getAgility());
        decals[1] = stats.renderize(stats.getStrength());
        decals[2] = stats.renderize(stats.getHardiness());
        decals[3] = stats.renderize(stats.getMana());
        decals[4] = stats.renderize(stats.getLife());
        decals[5] = stats.renderize(stats.getIntellect());
        decals[6] = stats.renderize(stats.getOffensiveRating());
        decals[7] = stats.renderize(stats.getDefensiveRating());

        return decals;
    }

    private class Pair {
        public final int index;
        public final ItemContainer container;

        public Pair(int index, ItemContainer container) {
            this.index = index;
            this.container = container;
        }
    }
}
