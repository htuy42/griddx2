package com.htuy.gridgame.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.main;

import java.util.function.Function;

public class Spawner extends BaseEntity {

    private final float delay;
    private final Function<Point, Entity> spawn;
    private float deltaSum = 0;

    public Spawner(float delay, Function<Point, Entity> spawn, Point location) {
        super(location);
        this.delay = delay;
        this.spawn = spawn;
    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {

    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        deltaSum += main.LAST_DELTA;
        if (deltaSum >= delay) {
            entities.spawnAtNextTick(spawn.apply(getLocation()));
            deltaSum = 0;
        }
        return false;
    }

}
