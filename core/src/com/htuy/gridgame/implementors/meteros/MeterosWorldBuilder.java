package com.htuy.gridgame.implementors.meteros;

import com.google.inject.Inject;
import com.htuy.gridgame.display.View;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.Spawner;
import com.htuy.gridgame.entity.physics_entity.GravityFaller;
import com.htuy.gridgame.entity.player.PlayerAvatar;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.world.WorldBuilder;

import java.util.Random;
import java.util.function.Function;

public class MeterosWorldBuilder implements WorldBuilder {

    private static Random r = new Random();
    private PlayerAvatar avatar;

    @Inject
    public MeterosWorldBuilder(PlayerAvatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public void buildEntities(GridProvider provider, EntityProvider entities) {
        View v = provider.getFullView();
        for (int x = 0; x < MeterosModule.NUM_METEORS; x++) {
            entities.spawnAtNextTick(new Spawner((float) Math.max(r.nextFloat() * MeterosModule.METEOR_FREQUENCY, 1.0), new Function<Point, Entity>() {
                @Override
                public Entity apply(Point point) {
                    Point dest = Point.randomPoint(v).withY(0);
                    return new GravityFaller(new Meteor(point, dest), false);
                }
            }, Point.randomPoint(v).withY(v.getHeight())));
        }
        entities.spawnAtNextTick(avatar);
    }


}
