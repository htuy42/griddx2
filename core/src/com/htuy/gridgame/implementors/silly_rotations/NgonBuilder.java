package com.htuy.gridgame.implementors.silly_rotations;

import com.google.inject.Inject;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.world.WorldBuilder;

import static com.htuy.gridgame.implementors.silly_rotations.SillyModule.gon;

public class NgonBuilder implements WorldBuilder {


    @Inject

    @Override
    public void buildEntities(GridProvider provider, EntityProvider entities) {
        entities.spawnAtNextTick(gon);
    }
}