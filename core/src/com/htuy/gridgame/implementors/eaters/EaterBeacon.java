package com.htuy.gridgame.implementors.eaters;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.main;

import java.util.Random;

public class EaterBeacon implements Entity {

    private static Random r = new Random();
    private Point location;
    private float deltaSum = 0;

    public EaterBeacon(Point location) {
        this.location = location;

    }

    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {

    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        deltaSum += main.LAST_DELTA;
        if (deltaSum > 12.0) {
            entities.spawnAtNextTick(new Eater(getLocation()));
            deltaSum = 0;
            Point newLoc = new Point(r.nextInt(grid.getFullView().getWidth()), r.nextInt(grid.getFullView().getHeight()));
            entities.updateEntityLocation(this, newLoc);
            location = newLoc;
        }
        return false;
    }

    @Override
    public Point getLocation() {
        return location;
    }
}
