package com.htuy.gridgame.world;

import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.renderer.Renderer;

import javax.inject.Inject;

public class BasicWorld implements GridWorld {
    private final GridProvider gridProvider;
    private final Renderer renderer;
    private final Display display;
    private final EntityProvider entityProvider;

    @Inject
    public BasicWorld(GridProvider gridProvider, Renderer renderer, Display display, EntityProvider entityProvider, WorldBuilder builder) {
        this.gridProvider = gridProvider;
        this.renderer = renderer;
        this.display = display;
        this.entityProvider = entityProvider;
        builder.buildGrid(gridProvider);
        builder.buildEntities(gridProvider, entityProvider);
    }



    @Override
    public void tick() {
        gridProvider.iterCells((point, cell) -> cell.tick(gridProvider, point, entityProvider));
        entityProvider.tickEntities(gridProvider);
    }

    @Override
    public void render() {
        renderer.render(gridProvider, display);
        entityProvider.renderEntities(display);
    }

    @Override
    public EntityProvider getEntityProvider() {
        return entityProvider;
    }

    @Override
    public GridProvider getGridProvider() {
        return gridProvider;
    }

    @Override
    public Renderer getRenderer() {
        return renderer;
    }
}
