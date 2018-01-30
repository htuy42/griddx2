package com.htuy.gridgame.world;

import com.htuy.gridgame.entity.EntityProvider;

public interface GridWorld {

    void tick();

    void render();

    EntityProvider getEntityProvider();
}
