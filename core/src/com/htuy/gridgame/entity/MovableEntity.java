package com.htuy.gridgame.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class MovableEntity implements Entity {

    protected float x;
    protected float y;
    private float dx;
    private float dy;
    private Point pLoc;

    private Entity inner;

    public MovableEntity(Entity inner) {
        this.inner = inner;
        this.x = inner.getLocation().getX();
        this.y = inner.getLocation().getY();
        this.pLoc = inner.getLocation();
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
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        inner.render(entities, display, renderer);
    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        x += dx;
        y += dy;
        Point newLoc = new Point((int) x, (int) y);
        entities.updateEntityLocation(getSelf(), newLoc);
        setLocation(newLoc);
        dx = 0;
        dy = 0;
        return false;
    }

    public void setLocation(Point p) {
        inner.setLocation(p);
    }


    @Override
    public Point getLocation() {
        return inner.getLocation();
    }

    @Override
    public Entity getSelf() {
        return inner.getSelf();
    }
}
