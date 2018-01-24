package com.htuy.gridgame.implementors.heatmap;

import com.htuy.gridgame.cell.Cell;
import com.htuy.gridgame.geom_tools.Point;
import com.htuy.gridgame.gridprovider.GridProvider;
import com.htuy.gridgame.main;

public class DistanceHeatCell implements Cell {

    private int height = 0;

    @Override
    public void tick(GridProvider provider, Point ownLocation) {
        height = (int) Math.floor(ownLocation.trueDistanceTo(main.inputRegistry.getCurrentMouseScreenLocation()));
    }

    @Override
    public int getHeight() {
        return height;
    }
}
