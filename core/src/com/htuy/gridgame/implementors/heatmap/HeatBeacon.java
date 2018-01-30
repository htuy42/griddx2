package com.htuy.gridgame.implementors.heatmap;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.htuy.gridgame.display.Display;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;

public class HeatBeacon implements Entity {

    private Point location;

    public HeatBeacon(Point location) {
        this.location = location;
    }


    @Override
    public void render(EntityProvider entities, Display display, ShapeRenderer renderer) {

    }

    @Override
    public boolean tick(GridProvider grid, EntityProvider entities) {
        return false;
    }

    @Override
    public Point getLocation() {
        return location;
    }
}