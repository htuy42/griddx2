package com.htuy.gridgame.world;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.renderer.Renderer;

import javax.inject.Inject;
import java.util.function.BiConsumer;

public class BasicWorld implements GridWorld {
    GridProvider provider;
    Renderer renderer;
    Display display;

    @Inject
    public BasicWorld(GridProvider provider, Renderer renderer, Display display) {
        this.provider = provider;
        this.renderer = renderer;
        this.display = display;
    }


    @Override
    public void tick() {
        provider.iterCells(new BiConsumer<Point, Cell>() {
            @Override
            public void accept(Point point, Cell cell) {
                cell.tick(provider, point);
            }
        });
    }

    @Override
    public void render() {
        renderer.render(provider, display);
    }
}
