package org.escaperun.game.model.stage.tile;

import org.escaperun.game.model.Collidable;
import org.escaperun.game.model.items.Item;
import org.escaperun.game.model.stage.tile.terrain.BlankTerrain;
import org.escaperun.game.model.stage.tile.terrain.Terrain;
import org.escaperun.game.serialization.Saveable;
import org.escaperun.game.view.Decal;
import org.escaperun.game.view.Renderable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public class Tile implements Renderable, Collidable, Saveable {

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

    @Override
    public Element save(Document dom, Element parent) {
        Element tile = dom.createElement("Tile");
        parent.appendChild(tile);

        terrain.save(dom, tile);
        if (item != null) {
            item.save(dom, tile);
        }
        return tile;
    }

    @Override
    public Tile load(Element node) {
        if (node == null) return null;
        Element tile;
        if (node.getElementsByTagName("Tile") != null && node.getElementsByTagName("Tile").getLength() > 0)
            tile = (Element) node.getElementsByTagName("Tile").item(0);
        else
            tile = node;
        if (tile == null) return null;

        Terrain loadedTerrain = new BlankTerrain().load(tile);
        Item item = null;
        if (tile.getElementsByTagName("Item") != null && node.getElementsByTagName("Item").getLength() > 0) {
            item = new Item(new Decal('f', Color.BLACK, Color.BLACK)) {

                @Override
                public boolean isCollidable() {
                    return false;
                }
            }.load(tile);
        }
        Tile res = new Tile(loadedTerrain);
        if (item != null) {
            res.placeItem(item);
        }
        return res;
    }
}
