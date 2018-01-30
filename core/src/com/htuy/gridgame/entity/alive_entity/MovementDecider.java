package com.htuy.gridgame.entity.alive_entity;

import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public interface MovementDecider {
    Point move(AliveEntity self, GridProvider gridProvider, EntityProvider entityProvider);
}
