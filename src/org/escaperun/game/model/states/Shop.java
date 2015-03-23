package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.controller.keyboard.KeyType;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.events.Timer;
import org.escaperun.game.model.items.Item;
import org.escaperun.game.model.items.Potion;
import org.escaperun.game.model.items.StrengthBooster;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.GameWindow;

import java.awt.*;
import java.util.ArrayList;

public class Shop extends GameState {

    private final int OFFSET = 11;
    private int COLUMN;
    private Playing previousState;
    private final String SHOP = "YE OLDE SHOP";
    private Timer moveTimer = new Timer(8);
    private Decal[][] window;
    private Potion healthPotion;
    private Potion strengthPotion;
    private Potion manaPotion;
    private Potion agilityPotion;
    private int selectedX = 0;
    private ArrayList<Item> shopItems;
    private Stage stage;

    public Shop(Playing previousState, Stage stage){
        this.stage = stage;
        this.previousState = previousState;
        healthPotion = new Potion(new Decal('E', Color.BLACK, Color.BLUE), "Elixir", "Replenishes health and other abilities boosters ;)");
        strengthPotion = new Potion(new Decal('P', Color.BLACK, Color.CYAN), "Strength Potion", "Boosts Strength of consumer");
        manaPotion = new Potion(new Decal('P', Color.BLACK, Color.YELLOW), "Mana Potion", "Restores depleted Mana");
        agilityPotion = new Potion(new Decal('P', Color.BLACK, Color.ORANGE), "Agility Potion", "Boosts Agility of consumer");
        shopItems = new ArrayList<Item>();
        shopItems.add(0, healthPotion);
        shopItems.add(1, strengthPotion);
        shopItems.add(2, manaPotion);
        shopItems.add(3,agilityPotion);
        selectedX = (GameWindow.COLUMNS / 2) - (shopItems.size()/2);
        COLUMN = (GameWindow.COLUMNS / 2) - (shopItems.size()/2);
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        boolean shop = pressed[bindings.getBinding(KeyType.SHOP)];
        if(shop){
            pressed[bindings.getBinding(KeyType.SHOP)] = false;
            return previousState;
        }
        handleMovement(bindings, pressed);
        return null;
    }

    @Override
    public Decal[][] getRenderable() {
        window = new Decal[GameWindow.ROWS][GameWindow.COLUMNS];
        renderMessage(3, SHOP);
        renderMessage(5, "Buy something or get the fuck out.");
        renderMessage(7, "_____________________________________");
        renderMessage(9, "ITEMS");
        for(int i = 0; i < shopItems.size(); i++){
            renderShopItems(OFFSET, COLUMN + i, shopItems.get(i));
        }
        renderMessage(13, "_____________________________________");
        if(shopItems.size() != 0) {
            renderItemDescription(17, 10, "ITEM NAME: ", shopItems.get(selectedX - COLUMN).getName());
            renderItemDescription(18, 10, "DESCRIPTION: ", shopItems.get(selectedX - COLUMN).getDescription());
        } else{
            renderItemDescription(17, 10, "ITEM NAME: ", "");
            renderItemDescription(18, 10, "DESCRIPTION: ", "There are no items left!");
        }

        return window;
    }

    private void renderMessage(int row, String message){
        int displayIndex = (GameWindow.COLUMNS / 2) - (message.length() / 2);
        for(int i = 0; i < message.length(); i++){
            window[row][i + displayIndex] = new Decal(message.charAt(i), Color.BLACK, Color.WHITE);
        }
    }

    private void renderItemDescription(int row, int col, String descripType, String description){
        String joinedDescription = descripType + description;
        for(int i = 0; i < joinedDescription.length(); i++){
            window[row][col + i] = new Decal(joinedDescription.charAt(i), Color.BLACK, Color.WHITE);
        }
    }

    private void renderShopItems(int row, int col, Item shopItem){
        if(isSelectedSkill(col)) {
            window[row][col] = new Decal(shopItem.getDecal().ch, Color.BLACK, Color.RED);
        }
        else{
            window[row][col] = shopItem.getDecal();
        }
    }


    private void handleMovement(KeyBindings bindings, boolean[] pressed){
        boolean enter = pressed[bindings.getBinding(KeyType.ACTION)];
        boolean right = pressed[bindings.getBinding(KeyType.RIGHT)];
        boolean left = pressed[bindings.getBinding(KeyType.LEFT)];

        moveTimer.tick();
        if(moveTimer.isDone()) {

            int moveX = selectedX;
            //System.out.println(moveX);
            if(right) moveX++;
            if(left) moveX--;
            if(enter) {
                if (shopItems.size() != 0){
                    stage.getAvatarInventory().add((TakeableItem)shopItems.get(selectedX - COLUMN));
                    shopItems.remove(selectedX - COLUMN);
                }
            }
            selectedX = clamp(moveX, COLUMN, COLUMN + shopItems.size() - 1);
            moveTimer.reset();
        }
    }

    private int clamp(int start, int min, int max){
        if(start > max) return max;
        if(start < min) return min;
        return start;
    }

    private boolean isSelectedSkill(int col){
        return (selectedX == col);
    }

}
