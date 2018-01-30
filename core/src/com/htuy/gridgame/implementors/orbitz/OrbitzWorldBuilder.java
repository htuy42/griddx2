package com.htuy.gridgame.implementors.orbitz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.DrawCircleEntity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.entity.MovableEntity;
import com.htuy.gridgame.entity.physics_entity.GravityFaller;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.world.WorldBuilder;

public class OrbitzWorldBuilder implements WorldBuilder {


    @Override
    public void buildEntities(GridProvider provider, EntityProvider entities) {
        for (int x = 0; x < 15; x++) {
            MovableEntity me = new MovableEntity(Point.randomPoint(provider.getFullView())) {
                @Override
                public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {

                }
            };
            entities.spawnAtNextTick(new DrawCircleEntity(new GravityFaller(me, true), Color.FIREBRICK, .8f));
        }
    }
}