package com.htuy.gridgame.implementors.eaters;

import com.htuy.gridgame.display.View;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.Spawner;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.world.WorldBuilder;

import java.util.Random;
import java.util.function.Function;

public class EaterWorldBuilder implements WorldBuilder {

    @Override
    public void buildEntities(GridProvider provider, EntityProvider entities) {
        Random r = new Random();
        View v = provider.getFullView();
        int width = v.getWidth();
        int height = v.getHeight();
        for (int x = 0; x < 1; x++) {
//            entities.spawnAtNextTick(new Eater(new Point(r.nextInt(width),r.nextInt(height))));
//            entities.spawnAtNextTick(new EaterEater(new Point(r.nextInt(width),r.nextInt(height))));
            entities.spawnAtNextTick(new Spawner(4f, new Function<Point, Entity>() {
                @Override
                public Entity apply(Point point) {
                    return new Eater(point);
                }
            }, Point.randomPoint(v)));
            entities.spawnAtNextTick(new Spawner(3f, new Function<Point, Entity>() {
                @Override
                public Entity apply(Point point) {
                    return new EaterEater(point);
                }
            }, Point.randomPoint(v)));
            entities.spawnAtNextTick(new Spawner(3f, new Function<Point, Entity>() {
                @Override
                public Entity apply(Point point) {
                    return new Eater3(point);
                }
            }, Point.randomPoint(v)));
        }
    }
}
