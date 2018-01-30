package com.htuy.gridgame.world;

import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.gridprovider.GridProvider;

public interface WorldBuilder {

    default void buildGrid(GridProvider provider) {

    }

    default void buildEntities(GridProvider provider, EntityProvider entities) {

    }

}
