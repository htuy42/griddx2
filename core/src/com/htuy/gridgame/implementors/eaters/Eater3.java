package com.htuy.gridgame.implementors.eaters;

import com.badlogic.gdx.graphics.Color;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.alive_entity.AliveEntity;
import com.htuy.gridgame.entity.alive_entity.CopyBirther;
import com.htuy.gridgame.entity.alive_entity.MovementDecider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

import java.util.function.Function;

public class Eater3 extends AliveEntity {

    public Eater3(Point location) {
        super(Color.BLUE, .55f, location, 50, new MovementDecider() {
            @Override
            public Point move(AliveEntity self, GridProvider gridProvider, EntityProvider entityProvider) {
                Entity closest = entityProvider.getNearestOfType(new Function<Entity, Boolean>() {
                    @Override
                    public Boolean apply(Entity entity) {
                        if (entity instanceof AliveEntity) {
                            AliveEntity ae = (AliveEntity) entity;
                            return ae.getSize() < self.getSize() && ae.getSize() * 2 > self.getSize();
                        }
                        return false;
                    }
                }, self.getLocation());
                if (closest == null) {
                    return gridProvider.randomAdjoiningLocations(self.getLocation());
                } else {
                    return self.getLocation().closerTo(closest.getLocation());
                }
            }
        }, new EaterEater.Eater2(), new CopyBirther(), 350, 35);
    }
}
