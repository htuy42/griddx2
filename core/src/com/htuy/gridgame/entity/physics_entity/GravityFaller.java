package com.htuy.gridgame.entity.physics_entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.MovableEntity;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class GravityFaller implements Entity {

    private MovableEntity inner;

    private Vector2 speed;

    private boolean towards;

    public GravityFaller(Entity entity, boolean towards) {
        speed = new Vector2(0, 0);
        this.towards = towards;
        if (entity instanceof MovableEntity) {
            this.inner = (MovableEntity) entity;
        } else {
            this.inner = new MovableEntity(entity);
        }
    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        inner.render(entities, display, renderer);


    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        if (towards) {
            doTowardsGravity();
        } else {
            doDownGravity();
        }
        doFriction();
        inner.tick(grid, entities);
        inner.move(speed.x, speed.y);

        return false;
    }

    @Override
    public void setLocation(Point location) {
        inner.setLocation(location);
    }

    @Override
    public Entity getSelf() {
        return inner.getSelf();
    }

    private void doDownGravity() {
        speed.add(Physics.GRAVITY);
        if (speed.len2() > Physics.TERMINAL_VELOCITY_SQ) {
            speed.setLength2(Physics.TERMINAL_VELOCITY_SQ);
        }
    }

    private void doFriction() {
        speed.scl(1.0f - Physics.FRICTION_COEFFICIENT);
    }

    @Override
    public Point getLocation() {
        return inner.getLocation();
    }

    private void doTowardsGravity() {
        Point loc = getLocation();
        for (Entity e : Physics.GRAVITY_CENTERS) {
            Point p = e.getLocation();
            Vector2 direction = new Vector2(p.getX() - loc.getX(), p.getY() - loc.getY());
            float dst = 0;
            if (Physics.FORCE_LAWS == 2) {
                dst = p.sqDistanceTo(loc);
            } else {
                double m = p.trueDistanceTo(loc);
                dst = 1;
                for (int x = 0; x < Physics.FORCE_LAWS; x++) {
                    dst *= m;
                }
            }
            direction.setLength2(Physics.GRAVITY_SPEED / dst);
            speed.x += direction.x;
            speed.y += direction.y;
            if (speed.len2() > Physics.TERMINAL_VELOCITY_SQ) {
                speed.setLength2(Physics.TERMINAL_VELOCITY_SQ);
            }
        }
    }
}
