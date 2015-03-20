package org.escaperun.game.model.stage.tile;

import org.escaperun.game.model.Collidable;
import org.escaperun.game.model.items.Item;
import org.escaperun.game.model.stage.tile.terrain.Terrain;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;

public class Tile implements Renderable, Collidable {

    // terrain is *NEVER* null
    private Terrain terrain;

    // item *MAY* be null
    // preconditions for placeItem/removeItem are made to reflect and enforce
    // that only one item can be on a Tile and it makes it so Items can't
    // disappear (from bugs) unless we specifically make them (bad logic)
    private Item item;

    public Tile(Terrain terrain) {
        if (terrain == null) throw new RuntimeException("terrain is null");
        this.terrain = terrain;
    }

    public boolean hasItem() {
        return item != null;
    }

    public void placeItem(Item item) {
        if (hasItem()) throw new RuntimeException("you can't place an item on a tile with item on it");
        this.item = item;
    }

    public Item removeItem() {
        if (!hasItem()) throw new RuntimeException("you can't take an item on a tile with no item on it");
        return item;
    }

    public void setTerrain(Terrain terrain) {
        if (terrain == null) throw new IllegalArgumentException("terrain is null");
        this.terrain = terrain;
    }

    @Override
    public Decal[][] getRenderable() {
        if (item != null) return item.getRenderable();
        return terrain.getRenderable();
    }

    @Override
    public boolean isCollidable() {
        boolean collidable = terrain.isCollidable();
        if (hasItem()) collidable |= item.isCollidable();
        return collidable;
    }
}
