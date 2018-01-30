package com.htuy.gridgame.entity.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.MovableEntity;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class PlayerAvatar extends MovableEntity {
    float health;
    float speed;


    public PlayerAvatar(Point location, float health, float speed) {
        super(location);
        this.health = health;
        this.speed = speed;
    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        super.tick(grid, entities);
        return health < 0;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {
        renderCircleSelf(Color.CYAN, 3.0f, renderer, display);
    }

    public void damage(float damage) {
        health -= damage;
    }

}
