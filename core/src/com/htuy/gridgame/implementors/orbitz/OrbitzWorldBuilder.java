package com.htuy.gridgame.implementors.orbitz;

import com.google.inject.Inject;
import com.htuy.gridgame.entity.BaseEntity;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.physics_entity.GravityFaller;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.world.WorldBuilder;

public class OrbitzWorldBuilder implements WorldBuilder {


    @Inject

    @Override
    public void buildEntities(GridProvider provider, EntityProvider entities) {
        for (int x = 0; x < 1000; x++) {
            Entity e = new BaseEntity(Point.randomPoint(provider.getFullView()));
            entities.spawnAtNextTick(new GravityFaller(e, true));
//            Physics.GRAVITY_CENTERS.add(e);
        }
    }
}