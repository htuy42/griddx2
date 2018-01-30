package com.htuy.gridgame.implementors.heatmap;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.entity.Entity;
import com.htuy.gridgame.entity.EntityProvider;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.main;

import java.util.List;

public class DistanceHeatCell implements Cell {

    private int height = 0;

    @Override
    public void tick(GridProvider provider, Point ownLocation, EntityProvider entities) {
        List<Entity> beacons = entities.getAllOfType(HeatBeacon.class);
        int cur = 0;
        for (Entity hb : beacons) {
            double dist = ownLocation.manhattanDistanceTo(hb.getLocation());
            cur += Math.max(0, (100 - dist));
        }
        cur += Math.max(0, (100 - ownLocation.manhattanDistanceTo(main.inputRegistry.getCurrentMouseScreenLocation())));
        height = cur;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int i) {

    }
}
