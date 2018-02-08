package com.htuy.gridgame.implementors.eaters;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.BaseEntity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.main;

import java.util.Random;

public class EaterBeacon extends BaseEntity {

    private static Random r = new Random();
    private float deltaSum = 0;

    public EaterBeacon(Point location) {
        super(location);

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
            Point newLoc = Point.randomPoint(grid.getFullView());
            entities.updateEntityLocation(this, newLoc);
            setLocation(newLoc);
        }
        return false;
    }

}
