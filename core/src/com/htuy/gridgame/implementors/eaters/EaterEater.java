package com.htuy.gridgame.implementors.eaters;

import com.badlogic.gdx.graphics.Color;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.alive_entity.AliveEntity;
import com.htuy.gridgame.entity.alive_entity.CopyBirther;
import com.htuy.gridgame.entity.alive_entity.EnergizeDecider;
import com.htuy.gridgame.entity.alive_entity.RandomMover;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

import java.util.List;

public class EaterEater extends AliveEntity {
    public EaterEater(Point location) {
        super(Color.RED, .3f, location, 50, new RandomMover(), new Eater2(), new CopyBirther(), 100, 6.0f);
    }

    public static class Eater2 implements EnergizeDecider {
        @Override
        public void energize(AliveEntity self, GridProvider gridProvider, EntityProvider entityProvider) {
            List<Entity> adjacentFood = entityProvider.getAllNearby(self.getLocation(), 1);
            for (Entity e : adjacentFood) {
                if (e instanceof AliveEntity) {
                    AliveEntity ae = (AliveEntity) e;
                    if (ae.getSize() < self.getSize() && ae.getSize() * 2 > self.getSize()) {
                        entityProvider.kill(e);
                        self.addEnergy((int) ae.getEnergy());
                    }
                }
            }
        }
    }

}
