package com.htuy.gridgame.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public interface Entity {

    void render(EntityProvider entities, Display display, ShapeRenderer renderer);

    // return true if the entity is now dead, false otherwise
    boolean tick(GridProvider grid, EntityProvider entities);

    default void renderCircleSelf(Color color, float radius, ShapeRenderer renderer, Display display) {
        renderer.setColor(color);
        Point transformed = display.toScreenLocation(getLocation());
        radius *= display.getCellSize();
        renderer.circle(transformed.getX(), transformed.getY(), radius);
    }

    Point getLocation();

}
