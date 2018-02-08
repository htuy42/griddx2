package com.htuy.gridgame.renderer.display_renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.google.inject.Inject;
import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.collections.CacheMap;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.renderer.Renderer;

public class SimpleHeightmapRenderer implements Renderer {
    private final ShapeRenderer renderer;
    private final CacheMap<Integer, Color> colorProvider;


    @Inject
    public SimpleHeightmapRenderer(ColorProvider colorProvider) {
        renderer = new ShapeRenderer();
        this.colorProvider = new CacheMap<>(colorProvider);
    }


    @Override
    public void render(GridProvider provider, Display display) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        provider.iterCells((point, cell) -> renderSingleCell(point.getX(), point.getY(), cell, display.getCellSize()));
        renderer.end();
    }

    private void renderSingleCell(int x, int y, Cell c, int cellSize) {
        renderer.setColor(colorProvider.retrieve(c.getHeight()));
        renderer.rect(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    public void clearMap() {
        this.colorProvider.clear();
    }
}