package com.htuy.gridgame.implementors.meteros;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.BaseEntity;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.MovableEntity;
import com.htuy.gridgame.entity.player.PlayerAvatar;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.main;

import java.util.List;

public class Meteor extends MovableEntity {
    Point dest;
    Vector2 path;

    public Meteor(Point loc, Point dest) {
        super(new BaseEntity(loc));
        this.dest = dest;
        this.path = new Vector2(dest.getX() - x, dest.getY() - y);
        path.setLength(MeterosModule.METEOR_SPEED);
    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        renderCircleSelf(Color.FIREBRICK, MeterosModule.METEOR_SIZE, renderer, display);
    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        move((float) (path.x * main.LAST_DELTA), (float) (path.y * main.LAST_DELTA));
        super.tick(grid, entities);

        List<Entity> nearbies = entities.getAllNearby(getLocation(), (int) MeterosModule.METEOR_SIZE);
        for (Entity e : nearbies) {
            if (e instanceof PlayerAvatar) {
                PlayerAvatar pa = (PlayerAvatar) e;
                pa.damage(MeterosModule.METEOR_DAMAGE);
                return true;
            }
        }
        return false;
    }
}
