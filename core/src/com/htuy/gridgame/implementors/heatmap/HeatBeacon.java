package com.htuy.gridgame.implementors.heatmap;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.BaseEntity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class HeatBeacon extends BaseEntity {


    public HeatBeacon(Point location) {
        super(location);
    }


    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {

    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        return false;
    }

}