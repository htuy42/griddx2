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

    public GravityFaller(MovableEntity entity, boolean towards) {
        this.inner = entity;
        speed = new Vector2(0, 0);
        this.towards = towards;
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
        inner.move(speed.x, speed.y);
        inner.tick(grid, entities);
        return false;
    }

    private void doTowardsGravity() {
        Point loc = getLocation();
        Vector2 direction = new Vector2(Physics.GRAVITY_CENTER.getX() - loc.getX(), Physics.GRAVITY_CENTER.getY() - loc.getY());
        direction.setLength2(Physics.GRAVITY_SPEED);
        speed.x += direction.x;
        speed.y += direction.y;
        if (speed.len2() > Physics.TERMINAL_VELOCITY_SQ) {
            speed.setLength2(Physics.TERMINAL_VELOCITY_SQ);
        }
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
}
