package com.htuy.gridgame.world;

import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.renderer.Renderer;

import javax.inject.Inject;

public class BasicWorld implements GridWorld {
    private final GridProvider provider;
    private final Renderer renderer;
    private final Display display;

    @Inject
    public BasicWorld(GridProvider provider, Renderer renderer, Display display) {
        this.provider = provider;
        this.renderer = renderer;
        this.display = display;
    }


    @Override
    public void tick() {
        provider.iterCells((point, cell) -> cell.tick(provider, point));
    }

    @Override
    public void render() {
        renderer.render(provider, display);
    }
}
