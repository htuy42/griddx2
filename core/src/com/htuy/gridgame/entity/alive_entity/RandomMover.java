package com.htuy.gridgame.entity.alive_entity;

import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class RandomMover implements MovementDecider {
    @Override
    public Point move(AliveEntity self, GridProvider gridProvider, EntityProvider entityProvider) {
        return gridProvider.randomAdjoiningLocations(self.getLocation());
    }
}
