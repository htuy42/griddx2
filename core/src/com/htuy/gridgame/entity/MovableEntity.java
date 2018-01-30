package com.htuy.gridgame.entity;

import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public abstract class MovableEntity implements Entity {

    protected Point location;
    protected float x;
    protected float y;
    private float dx;
    private float dy;

    protected MovableEntity(Point location) {
        this.location = location;
        x = location.getX();
        y = location.getY();
    }

    public void move(float dx, float dy) {
        this.dx += dx;
        this.dy += dy;
    }

    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        x += dx;
        y += dy;
        Point newLoc = new Point((int) x, (int) y);
        entities.updateEntityLocation(this, newLoc);
        location = newLoc;
        dx = 0;
        dy = 0;
        return false;
    }

    @Override
    public Point getLocation() {
        return location;
    }
}
