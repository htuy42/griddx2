package com.htuy.gridgame.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class DrawCircleEntity implements Entity {

    private Entity inner;

    private Color color;
    private float radius;

    public DrawCircleEntity(Entity entity, Color color, float radius) {
        this.inner = entity;
        this.color = color;
        this.radius = radius;
    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        renderCircleSelf(color, radius, renderer, display);
    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        inner.tick(grid, entities);
        return false;
    }

    @Override
    public Point getLocation() {
        return inner.getLocation();
    }
}
