package com.htuy.gridgame.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class BaseEntity implements Entity {

    private Point location;

    public BaseEntity(Point location) {
        this.location = location;
    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        //
    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        return false;
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Entity getSelf() {
        return this;
    }
}
