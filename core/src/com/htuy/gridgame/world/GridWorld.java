package com.htuy.gridgame.world;

import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.renderer.Renderer;

public interface GridWorld {

    void tick();

    void render();

    EntityProvider getEntityProvider();

    GridProvider getGridProvider();

    Renderer getRenderer();
}
