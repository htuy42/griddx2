package com.htuy.gridgame.renderer.display_renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.google.inject.Inject;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.collections.CacheMap;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.display.Screen;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.gridprovider.IterTools;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.renderer.Renderer;
import sun.security.provider.SHA;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SimpleHeightmapRenderer implements Renderer {
    private ShapeRenderer renderer;
    private CacheMap<Integer,Color> colorProvider;


    @Inject
    public SimpleHeightmapRenderer(ColorProvider colorProvider){
        renderer = new ShapeRenderer();
        this.colorProvider = new CacheMap<Integer, Color>(colorProvider);
    }


    @Override
    public void render(GridProvider provider, Display display) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        provider.iterCells(new BiConsumer<Point, Cell>() {
            @Override
            public void accept(Point point, Cell cell) {
                renderSingleCell(point.getX(),point.getY(),cell,display.getCellSize());
            }
        });
        renderer.end();
    }

    private void renderSingleCell(int x, int y, Cell c, int cellSize){
        renderer.setColor(colorProvider.retrieve(c.getHeight()));
        renderer.rect(x * cellSize,y*cellSize,cellSize,cellSize);
    }
}