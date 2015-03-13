package org.escaperun.game.model.entities;

import javafx.geometry.Pos;
import org.escaperun.game.model.Direction;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.items.equipment.EquipableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Decal;

import static java.lang.Math.abs;

public abstract class Avatar extends Entity {

    public Avatar(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public Avatar(Decal decal, Position initialPosition, EquipmentContainer<ArmorItem, WeaponItem> equipment) {
        super(decal, initialPosition, equipment);
    }

    @Override
    public void equipItem(EquipableItem item) {
        item.equip(this);
    }

    @Override
    public void move(Position p) {
        Position newP = getCurrentPosition();
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;
        int diffX = p.x - newP.x;
        int diffY = p.y - newP.y;

        //Set direction
        if (diffX < 0)
            up = true;
        else if (diffX > 0)
            down = true;

        if (diffY < 0)
            left = true;
        else if (diffY > 0)
            right = true;

        if (up) {
            if (right)
                setDirection(Direction.DEG_45);
            else if (left)
                setDirection(Direction.DEG_135);
            else
                setDirection(Direction.DEG_90);
        } else if (down) {
            if (right)
                setDirection(Direction.DEG_315);
            else if (left)
                setDirection(Direction.DEG_225);
            else
                setDirection(Direction.DEG_270);
        } else if (left)
            setDirection(Direction.DEG_180);
        else if (right)
            setDirection(Direction.DEG_0);
        setCurrentPosition(p);
    }

    public boolean tryMove(Position p) {
        //TODO: check if the movement cooldown that is based off of movement points. Try false if too soon.
        move(p);
        return true;
    }
}
