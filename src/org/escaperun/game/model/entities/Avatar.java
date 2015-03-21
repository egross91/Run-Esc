package org.escaperun.game.model.entities;

import com.sun.org.glassfish.external.statistics.Statistic;
import org.escaperun.game.model.Position;
import org.escaperun.game.model.entities.containers.EquipmentContainer;
import org.escaperun.game.model.entities.containers.ItemContainer;
import org.escaperun.game.model.entities.skills.Projectile;
import org.escaperun.game.model.items.TakeableItem;
import org.escaperun.game.model.items.equipment.armors.ArmorItem;
import org.escaperun.game.model.items.equipment.weapons.WeaponItem;
import org.escaperun.game.view.Decal;

public abstract class Avatar extends Entity {

    protected Avatar(Decal decal, Position initialPosition) {
        super(decal, initialPosition);
    }

    public Statistic getAvatarStatistics(){
        return null;
    }

    public abstract Projectile skill1();

}
